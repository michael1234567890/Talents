package com.phincon.talents.app.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.TMBalanceRepository;
import com.phincon.talents.app.dao.TMRequestHeaderRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.BenefitDTO;
import com.phincon.talents.app.dto.BenefitDetailDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.TMBalance;
import com.phincon.talents.app.model.hr.TMRequest;
import com.phincon.talents.app.model.hr.TMRequestHeader;
import com.phincon.talents.app.utils.Utils;

@Service
public class TMRequestHeaderService {

	@Autowired
	TMRequestHeaderRepository tmRequestHeaderRepository;
	@Autowired
	TMRequestRepository tmRequestRepository;

	@Autowired
	TMBalanceRepository tmBalanceRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;

	@Autowired
	TMRequestService tmRequestService;

	@Autowired
	TMBalanceService tmBalanceService;
	
	private Double BATAS_SPD_AMOUNT = Double.valueOf("2000000"); 
	private ObjectMapper objectMapper = new ObjectMapper();

	@Transactional
	public TMRequestHeader findById(Long id) {
		return tmRequestHeaderRepository.findOne(id);
	}

	@Transactional
	public TMRequestHeader findByIdWithDetail(Long id) {
		TMRequestHeader obj = tmRequestHeaderRepository.findOne(id);
		List<TMRequest> details = tmRequestRepository.findByTmRequestHeader(id);
		obj.setDetails(details);
		return obj;
	}

	@Transactional
	public TMRequestHeader findByIdWithDetail(Long id, Long employeeId) {
		TMRequestHeader obj = tmRequestHeaderRepository.findByEmployeeAndId(
				employeeId, id);
		if(obj.getRefRequestHeader() != null){
			TMRequestHeader refRequestHeader = tmRequestHeaderRepository.findOne(obj.getRefRequestHeader());
			obj.setRefRequestHeaderObj(refRequestHeader);
		}
		
		List<TMRequest> details = tmRequestRepository.findByTmRequestHeader(id);
		obj.setDetails(details);
		return obj;
	}

	@Transactional
	public void save(TMRequestHeader obj) {
		tmRequestHeaderRepository.save(obj);
	}

	public void clearBenefitDetail(BenefitDTO request) {
		List<BenefitDetailDTO> listBenefitDetail = new ArrayList<BenefitDetailDTO>();
		if (request.getCategoryType().equals(TMRequest.CAT_MEDICAL)) {
			List<BenefitDetailDTO> details = new ArrayList<BenefitDetailDTO>();
			BenefitDetailDTO medical = new BenefitDetailDTO();
			Double amount = getTotalDetails(request.getDetails());
			medical.setAmount(amount);
			medical.setType(TMRequest.CAT_MEDICAL);
			String data = null;
			try {
				data = this.objectMapper.writeValueAsString(request
						.getDetails());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			medical.setData(data);
			details.add(medical);
			request.setDetails(details);
		}

		for (BenefitDetailDTO benefitDetailDTO : request.getDetails()) {
			if (benefitDetailDTO.getAmount() != null
					&& benefitDetailDTO.getAmount() > 0) {
				listBenefitDetail.add(benefitDetailDTO);
			}
		}

		request.setDetails(listBenefitDetail);

	}

	@Transactional
	public BenefitDTO verificationBenefit(BenefitDTO request, User user,
			Employment employment, Employment requester) {
		if (request.getModule() == null || request.getCategoryType() == null) {
			throw new RuntimeException(
					"Module and Category Type can't be Empty.");
		}
		if (request.getDetails() == null || request.getDetails().size() == 0) {
			throw new RuntimeException("Your request can't be Empty.");
		}
		
		List<TMBalance> listBalance = getListBalance(request, user, employment);
		processSubmitedClaim(request, listBalance, user);
		return request;
	}

	@Transactional
	private void processSubmitedClaim(BenefitDTO request,
			List<TMBalance> listBalance, User user) {
		Date startDate = request.getStartDate();
		Date endDate = request.getEndDate();
		if(!request.getCategoryType().toLowerCase().equals("perjalanan dinas") && !request.getCategoryType().toLowerCase().equals("spd advance")){
			endDate = null;
		}
		
		validationInputBenefitProcess(request,user);
		List<BenefitDetailDTO> listBenefitDetailDTOs = new ArrayList<BenefitDetailDTO>();
		clearBenefitDetail(request);
		Double totalHeaderAmount = Double.valueOf("0");
		Double totalHeaderAmountSubmit = Double.valueOf("0");
		if( request.getDetails() != null &&  request.getDetails().size() >0){
			for (BenefitDetailDTO benefitDetail : request.getDetails()) {
				Long qty = benefitDetail.getQty();
				if (qty == null || qty ==0) {
					qty = 1L;
					if (startDate != null && endDate != null)
						qty = Utils.diffDay(startDate, endDate) + 1L;
				}

				benefitDetail.setQty(qty);
				Double totalClaim = benefitDetail.getAmount();
				Double totalClaimBalance = totalClaim;
				benefitDetail.setTotalClaim(totalClaim);
				
				TMBalance tmBalance = tmBalanceService.getByModuleTypeFromList(
						listBalance, request.getModule(), benefitDetail.getType());
				
				boolean passed = true;
				// Sumbangan Perabot
				if(benefitDetail.getType().toLowerCase().equals("sumbangan perabot")){
					sumbanganPerabotValidation(benefitDetail,listBalance,  user, totalClaim);
					totalClaim = benefitDetail.getTotalClaim();
					totalClaimBalance = totalClaim;
					passed = false;
				}
				
				if(benefitDetail.getType().toLowerCase().equals("cuti besar")){
					if(tmBalance != null) {
						totalClaim = tmBalance.getBalanceEnd();
						benefitDetail.setAmount(totalClaim);
						benefitDetail.setTotalClaim(totalClaim);
						totalClaimBalance = totalClaim;
					}
					
					passed = false;
				}
				
				if(benefitDetail.getType().toLowerCase().equals("taxi") || benefitDetail.getType().toLowerCase().equals("transport") || benefitDetail.getType().toLowerCase().equals("rental mobil") || benefitDetail.getType().toLowerCase().equals("laundry") || benefitDetail.getType().toLowerCase().equals("tol parkir bensin") || benefitDetail.getType().toLowerCase().equals("other")){
					passed = false;
					totalClaimBalance = totalClaim;
				}
				
				
				if (tmBalance != null && !tmBalance.getBalanceType().toLowerCase()
								.equals("one time (transaction)") && passed) {
					
					System.out.println(tmBalance.getType() + " : "
							+ tmBalance.getBalanceEnd());
					
					if(benefitDetail.getType().toLowerCase().equals("medical"))
						benefitDetail.setTotalSubmitedClaim(tmBalance.getBalanceUsed());
					else
						benefitDetail.setTotalSubmitedClaim(0D);
					
					totalClaimBalance = qty.doubleValue() * tmBalance.getBalanceEnd();
					benefitDetail.setLastClaimDate(tmBalance.getLastClaimDate());
					System.out.println("Qty: " + qty );
					System.out.println("Total Claim : " + totalClaim );
					System.out.println("total Claim Balance : " + totalClaimBalance );
					
					
					if (totalClaimBalance < totalClaim)
						benefitDetail.setTotalCurrentClaim(totalClaimBalance);
					else
						benefitDetail.setTotalCurrentClaim(totalClaim);
					
					totalClaimBalance = benefitDetail.getTotalCurrentClaim();
				} else {
					benefitDetail.setTotalCurrentClaim(totalClaim);
				}
				
				
				
				listBenefitDetailDTOs.add(benefitDetail);
				totalHeaderAmountSubmit += totalClaim;
				totalHeaderAmount += totalClaimBalance;
				
			}
			request.setTotal(totalHeaderAmount);
			request.setTotalSubmit(totalHeaderAmountSubmit);
			request.setDetails(listBenefitDetailDTOs);
		}
		
		
		if (listBenefitDetailDTOs.size() > 0)
			
		
		request.setVerified(true);
	}

	private void validationInputBenefitProcess(BenefitDTO request,User user) {
		boolean validationBeforeStartDate = true;
		boolean validationNextStartDate = false;
		if(request.getCategoryType().toLowerCase().equals("spd advance")) {
			validationBeforeStartDate = false;
			validationNextStartDate = true;
		}
		
		if(request.getCategoryType().toLowerCase().equals("perjalanan dinas") || request.getCategoryType().toLowerCase().equals("spd advance")){
			List<TMRequestHeader> listHeaderRequest = tmRequestHeaderRepository.findBetweenStartEndDate(user.getCompany(), user.getEmployee(), request.getModule(), request.getCategoryType(), request.getStartDate());
			if(listHeaderRequest != null && listHeaderRequest.size() > 0){
				throw new RuntimeException(
						"You are not allowed request in the range date.");
			}
			
			if (Utils.comparingDate(request.getEndDate(),
					request.getStartDate(), "<")) {
				throw new RuntimeException(
						"The End date must be greater than The start date.");
			}
		}
		
		if(validationBeforeStartDate) {
			if(Utils.diffDay(request.getStartDate(), new Date()) < 0) {
				throw new RuntimeException(
						"Your transaction Date must be less than Today.");
			}
		}
		
		if(validationNextStartDate) {
			if(Utils.diffDay(request.getStartDate(), new Date()) > -1) {
				throw new RuntimeException(
						"Your Start Date must be earlier than Today.");
			}
		}
		
		if(request.getCategoryType().toLowerCase().equals("kacamata") || request.getCategoryType().toLowerCase().equals("medical")) {
			if(Utils.diffDay(request.getStartDate(), new Date()) > 30) {
				throw new RuntimeException(
						"Your transaction Date must be less than 30 days.");
			}
		}
		
	}

	@Transactional
	private List<TMBalance> getListBalance(BenefitDTO request, User user,
			Employment employment) {
		List<TMBalance> resultBalance = tmBalanceRepository
				.findBalanceByModuleEmployment(user.getCompany(),
						employment.getId(), request.getModule());

		/*
		 * List<TMBalance> resultBalance = tmBalanceRepository
		 * .findBalanceCategoryTypeByEmployment(user.getCompany(),
		 * employment.getId(), request.getModule(), request.getCategoryType());
		 */

		Date today = new Date();
		// get all type in categoryTYpe with module BN
		List<TMBalance> listBalance = new ArrayList<TMBalance>();

		for (TMBalance tmBalance : resultBalance) {
			if (tmBalance.getStartDate() != null
					&& tmBalance.getEndDate() != null) {
				if (Utils.comparingDate(today, tmBalance.getStartDate(), ">=")
						&& Utils.comparingDate(today, tmBalance.getEndDate(),
								"<=")) {
					// no limit is no need to add Map Balance
					if (tmBalance.getBalanceType() != null
							&& !tmBalance.getBalanceType().toLowerCase()
									.contains("no limit"))
						listBalance.add(tmBalance);
				}
			} else {
				// no limit is no need to add Map Balance
				if (tmBalance.getBalanceType() != null
						&& !tmBalance.getBalanceType().toLowerCase()
								.contains("no limit"))
					listBalance.add(tmBalance);
			}

		}
		return listBalance;
	}

	@Transactional
	public void createBenefit(BenefitDTO request, User user,
			Employment employment, Employment requester) {

		if (request.getModule() == null || request.getCategoryType() == null) {
			throw new RuntimeException(
					"Module and Category Type can't be Empty.");
		}
		if (request.getDetails() == null || request.getDetails().size() == 0) {
			throw new RuntimeException("Your request can't be Empty.");
		}

		List<TMBalance> listBalance = getListBalance(request, user, employment);
		processSubmitedClaim(request,listBalance,user);

	
		// move listBalance into MapBalance
		Map<String, Double> mapBalance = new HashMap<String, Double>();
		for (TMBalance tmBalance : listBalance) {
			mapBalance.put(tmBalance.getType().toLowerCase(),
					tmBalance.getBalanceEnd());
		}

		validationBenefitAmount(request, mapBalance, listBalance, user, employment);

		TMRequestHeader tmRequestHeader = new TMRequestHeader();
		tmRequestHeader.setCompany(user.getCompany());
		tmRequestHeader.setCreatedBy(user.getUsername());
		tmRequestHeader.setModifiedBy(user.getUsername());
		tmRequestHeader.setModifiedDate(new Date());
		tmRequestHeader.setCreatedDate(new Date());
		tmRequestHeader.setEmployee(user.getEmployee());
		tmRequestHeader.setRequestDate(new Date());
		tmRequestHeader.setRequester(user.getEmployee());
		tmRequestHeader.setModule(request.getModule());
		tmRequestHeader.setCategoryType(request.getCategoryType());
		tmRequestHeader.setCategoryTypeExtId(request.getCategoryTypeExtId());
		tmRequestHeader.setOrigin(request.getOrigin());
		tmRequestHeader.setDestination(request.getDestination());
		tmRequestHeader.setRemark(request.getRemark());
		tmRequestHeader.setNeedReport(false);
		tmRequestHeader.setTotalAmount(request.getTotal());
		tmRequestHeader.setTotalAmountSubmit(request.getTotalSubmit());
		tmRequestHeader.setPulangKampung(request.getPulangKampung());
		tmRequestHeader.setStartDate(request.getStartDate());
		tmRequestHeader.setEndDate(request.getEndDate());
		tmRequestHeader.setSpdType(request.getSpdType());
		if (request.getCategoryType() != null
				&& request.getCategoryType().toLowerCase()
						.equals("spd advance")) {
			tmRequestHeader.setNeedReport(true);
		}

		tmRequestHeader.setStatus(TMRequestHeader.APPROVED);
		Workflow workflow = null;
		String taskName = request.getWorkflow();
		
		if((tmRequestHeader.getCategoryType().toLowerCase().equals("perjalanan dinas")) && tmRequestHeader.getTotalAmount() > BATAS_SPD_AMOUNT){
			taskName = Workflow.SUBMIT_BENEFIT2_5;
		}
		
		workflow = workflowService.findByCodeAndCompanyAndActive(taskName,
				user.getCompany(), true);
		
		if (workflow != null) {
			tmRequestHeader.setStatus(TMRequestHeader.PENDING);
		}

		if(request.getLinkRefHeader() != null){
			tmRequestHeader.setRefRequestHeader(request.getLinkRefHeader());
		}
			
		tmRequestHeaderRepository.save(tmRequestHeader);

		if (request.getLinkRefHeader() != null) {
			tmRequestHeaderRepository.updateByNeedReportAndId(false,
					request.getLinkRefHeader());
		}

		

		for (BenefitDetailDTO details : request.getDetails()) {
			// create new TM Request
			TMRequest tmRequest = new TMRequest();
			tmRequest.setStartDate(request.getStartDate());
			tmRequest.setEndDate(request.getEndDate());

			if (request.getStartDate() != null && request.getEndDate() != null) {
				Double totalDay = Double.valueOf("1");
				if (Utils.comparingDate(request.getEndDate(),
						request.getStartDate(), "<")) {
					throw new RuntimeException(
							"The End date must be greater than The start date.");
				}
				Long diffDays = Utils.diffDay(request.getStartDate(),
						request.getEndDate());
				if (diffDays != null) {
					diffDays += 1;
					totalDay = diffDays.doubleValue();
				}

				tmRequest.setTotalDay(totalDay);
			}
			tmRequest.setOrigin(request.getOrigin());
			tmRequest.setDestination(request.getDestination());
			
			tmRequest.setAmountSubmit(details.getTotalClaim());
			tmRequest.setAmount(details.getTotalCurrentClaim());
			tmRequest.setType(details.getType());
			tmRequest.setCategoryType(request.getCategoryType());
			tmRequest.setCategoryTypeExtId(request.getCategoryTypeExtId());
			tmRequest.setModule(request.getModule());
			tmRequest.setRequestDate(new Date());
			tmRequest.setCreatedDate(new Date());
			tmRequest.setCreatedBy(user.getUsername());
			tmRequest.setModifiedBy(user.getUsername());
			tmRequest.setTmRequestHeader(tmRequestHeader.getId());
			tmRequest.setEmployment(employment.getId());
			tmRequest.setEmploymentExtId(employment.getExtId());
			tmRequest.setData(details.getData());
			tmRequest.setRequesterEmployment(requester.getId());
			tmRequest.setRequesterEmploymentExtId(requester.getExtId());
			tmRequest.setCompany(user.getCompany());
			tmRequest.setNeedSync(true);
			tmRequest.setRemark(request.getRemark());
			tmRequest.setStatus(TMRequest.APPROVED);
			tmRequest.setQty(details.getQty());
			if (workflow != null) {
				tmRequest.setNeedSync(false);
				tmRequest.setStatus(TMRequest.REQUEST);
			}
			tmRequestRepository.save(tmRequest);
		}

		// decrease Balance end and increase balance used in TMBalance
		Map<String, Double> mapBenefitDetail = new HashMap<String, Double>();
		for (BenefitDetailDTO benefitDetailDto : request.getDetails()) {
			if (benefitDetailDto.getType().toLowerCase()
					.equals("sumbangan pernikahan")) {
				Double objAmount = (Double) mapBalance.get(benefitDetailDto
						.getType().toLowerCase());
				mapBenefitDetail.put(benefitDetailDto.getType().toLowerCase(),
						objAmount);
			} else {
				mapBenefitDetail.put(benefitDetailDto.getType().toLowerCase(),
						benefitDetailDto.getTotalCurrentClaim());
			}

		}
		
		actionForBalance(listBalance, mapBenefitDetail, user);
		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO
					.setObjectName(TMRequestHeader.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(tmRequestHeader.getId());
			dataApprovalDTO.setTask(request.getWorkflow());
			dataApprovalDTO.setModule(workflow.getModule());
			if (request.getAttachments() != null
					&& request.getAttachments().size() > 0) {
				dataApprovalDTO.setAttachments(request.getAttachments());
			}
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}

	}
	

	private void actionForBalance(List<TMBalance> listBalance,
			Map<String, Double> mapBenefitDetail, User user) {
		for (TMBalance tmBalance : listBalance) {
			if (mapBenefitDetail.get(tmBalance.getType().toLowerCase()) != null) {
				Double decrease = Double.valueOf("0");
				decrease = mapBenefitDetail.get(tmBalance.getType()
						.toLowerCase());
				
				if (tmBalance.getBalanceType() != null
						&& ( (tmBalance.getBalanceType().toLowerCase()
							.equals("daily") || tmBalance.getBalanceType().toLowerCase()
							.equals("one time (transaction)") || tmBalance.getBalanceType().toLowerCase()
							.equals("one time (2 years)")|| tmBalance.getBalanceType().toLowerCase()
							.equals("one time (5 years)")) )){
					
					tmBalance.setBalanceUsed(decrease);
				} else {
					Double balanceEnd = tmBalance.getBalanceEnd() - decrease;
					tmBalance.setBalanceEnd(balanceEnd);
					Double balanceUsed = tmBalance.getBalanceUsed() + decrease;
					tmBalance.setBalanceUsed(balanceUsed);
				}

				if (tmBalance.getLastClaimDate() != null)
					tmBalance.setLastClaimDateBefore(tmBalance
							.getLastClaimDate());

				tmBalance.setLastClaimDate(new Date());
				if (tmBalance.getType().toLowerCase()
						.equals("sumbangan perabot")) {
					Employee employee = employeeRepository.findOne(user
							.getEmployee());
					tmBalance.setMaritalStatus(employee.getMaritalStatus());
				}
				tmBalanceRepository.save(tmBalance);
			}
		}
	}

	private Double getTotalDetails(List<BenefitDetailDTO> details) {
		Double total = Double.valueOf("0");
		if (details != null && details.size() > 0) {
			for (BenefitDetailDTO benefitDetailDTO : details) {
				if(benefitDetailDTO.getAmount() != null)
					total += benefitDetailDTO.getAmount();
			}
		}
		return total;
	}

	public void validationBenefitAmount(BenefitDTO obj,
			Map<String, Double> mapBalance, List<TMBalance> listBalance,
			User user, Employment employment) {

		if (obj.getDetails() != null && obj.getDetails().size() > 0) {
			for (BenefitDetailDTO details : obj.getDetails()) {
				
				validationBalanceType(obj, details, listBalance,
						employment.getId(), user.getCompany());

				Double detailAmount = Double.valueOf("0");
				if (details.getTotalCurrentClaim() != null)
					detailAmount = details.getTotalCurrentClaim();
				
				System.out.println("Map Balance Type " + details.getType());
				System.out.println(mapBalance.get(details.getType()
						.toLowerCase()));
				System.out.println("Current Claim " + detailAmount);
				
				boolean passed = true;
				
				if(obj.getCategoryType().toLowerCase().equals("perjalanan dinas") || obj.getCategoryType().toLowerCase().equals("spd advance") ){
					passed = false;
				}
				
				
				if (mapBalance.get(details.getType().toLowerCase()) != null
						&& mapBalance.get(details.getType().toLowerCase()) < detailAmount && passed) {
					throw new RuntimeException("Your Balance "
							+ details.getType() + " is not enought.");
				}
			}
		}

	}

	@Transactional
	public void approved(DataApproval dataApproval,
			ApprovalWorkflowDTO approvalWorkflow) {
		boolean isHaveMedicalOverlimit = false;
		Long headerId = dataApproval.getObjectRef();
		tmRequestHeaderRepository.approved(headerId);
		List<TMRequest> listTmRequest = tmRequestRepository
				.findByTmRequestHeader(headerId);
		for (TMRequest tmRequest : listTmRequest) {
			if (tmRequest.getType().toLowerCase().equals("medical overlimit"))
				isHaveMedicalOverlimit = true;
		}

		// update every tmrequest with header id = request id
		if (isHaveMedicalOverlimit) {
			System.out.println("Medical Overlimit Approval");
			Double amount = approvalWorkflow.getAmount();
			tmRequestService.approveMedicalOverlimit(headerId, listTmRequest,
					amount);
		} else {
			System.out.println("Non Medical Overlimit Approval");
			tmRequestService.ApproveWithHeaderId(headerId, listTmRequest);
		}

	}

	public void sumbanganPerabotValidation(BenefitDetailDTO detail,
			List<TMBalance> listBalance, User user, Double totalClaim) {
		Employee employee = employeeRepository.findOne(user.getEmployee());
		System.out.println("Type 3 : " + detail.getType().toLowerCase());
		if (detail.getType().toLowerCase().equals("sumbangan perabot")) {
			TMBalance balance = getBalanceByCategoryAndType(listBalance,
					"sumbangan perabot");
			if (employee.getMaritalStatus() == null
					|| employee.getMaritalStatus().equals("")) {
				throw new RuntimeException(
						"Your Marital Status is Unknown. Please contact your Admin");
			}

			if (balance.getLastClaimDate() != null) {
				// check marital status is same or not, it should be not
				if (balance.getMaritalStatus().equals(
						employee.getMaritalStatus())) {
					throw new RuntimeException("You are not allowed apply '"
							+ detail.getType()
							+ "' Your marital status is same with before.");
				}
			}
			
			if(totalClaim != null) {
				if(employee.getMaritalStatus().toLowerCase().equals("single") || employee.getMaritalStatus().toLowerCase().equals("divorce"))
					totalClaim = TMRequest.CLAIM_PERABOT_SINGLE;
				else 
					totalClaim = balance.getBalanceEnd();
				
				System.out.println("Balance End : " + balance.getBalanceEnd());
				if(balance.getBalanceEnd() <= Double.valueOf(0D))
					totalClaim = 0D;
				
				if(totalClaim < Double.valueOf(0D))
					totalClaim = 0D;
				
				detail.setTotalClaim(totalClaim);
				detail.setTotalCurrentClaim(totalClaim);
				detail.setTotalSubmitedClaim(balance.getBalanceUsed());
				System.out.println("totalClaim : " + totalClaim);
			}
			
			

		}
	}

	public void validationBalanceType(BenefitDTO header,
			BenefitDetailDTO tmRequest, List<TMBalance> listBalance,
			Long employmentId, Long companyId) {

		String type = tmRequest.getType();
		TMBalance balance = null;
		for (TMBalance tmBalance : listBalance) {
			if (tmBalance.getType().toLowerCase().equals(type.toLowerCase())) {
				balance = tmBalance;
			}
		}

		if (balance != null) {
			if (balance.getBalanceType() != null
					&& balance.getBalanceType().toLowerCase()
							.contains("one time")) {
				System.out.println("Balance ID " + balance.getId());
				System.out.println("Last Claim Date "
						+ balance.getLastClaimDate());

				if (balance.getBalanceType() != null
						&& balance.getBalanceType().toLowerCase()
								.equals("one time (2 years)")) {
					// check last claim date
					if (balance.getLastClaimDate() != null
							&& (Utils.diffDay(balance.getLastClaimDate(),
									new Date()) < 730)) {
						String strNextApply = "";
						Date nextApply = null;
						try {
							nextApply = Utils.addDay(balance.getLastClaimDate(), 730);
							strNextApply = Utils.convertDateToString(nextApply);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						throw new RuntimeException(
								"You have already applied '"
										+ type
										+ "'. You can apply again in "+strNextApply+".");
					}
				} else if (balance.getBalanceType() != null
						&& balance.getBalanceType().toLowerCase()
								.equals("one time (5 years)")) {
					if (balance.getLastClaimDate() != null
							&& (Utils.diffDay(balance.getLastClaimDate(),
									new Date()) < 1825)) {
						String strNextApply = "";
						Date nextApply = null;
						try {
							nextApply = Utils.addDay(balance.getLastClaimDate(), 1825);
							strNextApply = Utils.convertDateToString(nextApply);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						throw new RuntimeException(
								"You have already applied '"
										+ type
										+ "'. You can apply again in "+strNextApply+".");
					}
				} else if (balance.getBalanceType() != null
						&& balance.getBalanceType().toLowerCase()
								.equals("one time")) {
					if (balance.getLastClaimDate() != null) {
						throw new RuntimeException("You have already applied '"
								+ type + "'. This type Only one time.");
					}
				}

				if (balance.getType().toLowerCase().contains("lensa")) {
					if (isLensaHaveClaimDate(listBalance)) {
						throw new RuntimeException(
								"You have already applied '" + type
										+ "'. This type Only one time Apply.");

					}
				}

			}

//			if (balance.getBalanceType() != null
//					&& balance.getBalanceType().toLowerCase().contains("daily")) {
//				// Ignore if any record in request with same day transaction
//				// date
//				List<TMRequest> listRequestDaily = tmRequestRepository
//						.findTMRequestByStartDate(companyId, employmentId,
//								header.getModule(), header.getCategoryType(),
//								tmRequest.getType(), header.getStartDate());
//				if (listRequestDaily != null && listRequestDaily.size() > 0) {
//					throw new RuntimeException(
//							"Error : You have already applied. This type '"
//									+ tmRequest.getType()
//									+ "' Only one time per Daily.");
//				}
//			}
		}
	}

	public boolean isLensaHaveClaimDate(List<TMBalance> listBalance) {
		for (TMBalance tmBalance : listBalance) {
			if (tmBalance.getType().toLowerCase().contains("lensa") && tmBalance.getLastClaimDate() != null) {
				return true;
			}
		}
		return false;
	}

	public TMBalance getBalanceByCategoryAndType(List<TMBalance> listBalance,
			String type) {

		type = type.toLowerCase();
		for (TMBalance tmBalance : listBalance) {
			if (tmBalance.getType().toLowerCase().equals(type))
				return tmBalance;
		}
		return null;
	}

	public void rejected(DataApproval dataApproval) {
		Long headerId = dataApproval.getObjectRef();
		// header status rejected
		tmRequestHeaderRepository.rejected(headerId);
		// every tmrequest set status reject with header id = headerid
		tmRequestService.rejectWithHeaderId(headerId);

	}

	public void processingBeforeApproved(DataApproval dataApproval,ApprovalWorkflowDTO approvalWorkflowDTO) {
		TMRequestHeader objRequestHeader = tmRequestHeaderRepository.findOne(dataApproval.getObjectRef());
		if(objRequestHeader != null) {
			String categoryType = objRequestHeader.getCategoryType().toLowerCase();
			if(categoryType.equals("medical overlimit")) {
				Double amount = approvalWorkflowDTO.getAmount();
				tmRequestRepository.updateAmountByHeaderId(amount, objRequestHeader.getId());
				tmRequestHeaderRepository.updateTotalAmountById(amount, objRequestHeader.getId());
			}
		}
	}
}
