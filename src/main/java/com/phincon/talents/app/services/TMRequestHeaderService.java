package com.phincon.talents.app.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeePayrollRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.GroupRepository;
import com.phincon.talents.app.dao.HolidayRepository;
import com.phincon.talents.app.dao.PatternRepository;
import com.phincon.talents.app.dao.PayrollElementHeaderRepository;
import com.phincon.talents.app.dao.RequestCategoryTypeRepository;
import com.phincon.talents.app.dao.RequestTypeRepository;
import com.phincon.talents.app.dao.ShiftRepository;
import com.phincon.talents.app.dao.TMBalanceRepository;
import com.phincon.talents.app.dao.TMRequestHeaderRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.AttendanceRefDTO;
import com.phincon.talents.app.dto.BenefitDTO;
import com.phincon.talents.app.dto.BenefitDetailDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.dto.TotalCategoryDTO;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.Holiday;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.EmployeePayroll;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.Group;
import com.phincon.talents.app.model.hr.Pattern;
import com.phincon.talents.app.model.hr.PayrollElementHeader;
import com.phincon.talents.app.model.hr.RequestCategoryType;
import com.phincon.talents.app.model.hr.RequestType;
import com.phincon.talents.app.model.hr.Shift;
import com.phincon.talents.app.model.hr.TMBalance;
import com.phincon.talents.app.model.hr.TMRequest;
import com.phincon.talents.app.model.hr.TMRequestHeader;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.utils.Utils;

@Service
public class TMRequestHeaderService {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
	RunningNumberService runningNumberService;

	@Autowired
	PatternRepository patternRepository;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	GroupRepository groupAttendanceRepository;
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Autowired
	RequestCategoryTypeRepository requestCategoryTypeRepository;

	@Autowired
	RequestTypeRepository RequestTypeRepository;
	
	@Autowired
	EmployeePayrollRepository employeePayrollRepository;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	@Autowired 
	VwEmpAssignmentRepository vwEmpAssignmentRepository;
	
	
	@Autowired
	TMRequestService tmRequestService;

	@Autowired
	TMBalanceService tmBalanceService;

	@Autowired
	PayrollElementHeaderRepository payrollElementHeaderRepository;

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
		TMRequestHeader obj = null;
		if (employeeId == null)
			obj = tmRequestHeaderRepository.findOne(id);
		else
			obj = tmRequestHeaderRepository.findByEmployeeAndId(employeeId, id);

		if (obj.getRefRequestHeader() != null) {
			TMRequestHeader refRequestHeader = tmRequestHeaderRepository
					.findOne(obj.getRefRequestHeader());
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

		if (!request.getCategoryType().toLowerCase().equals("perjalanan dinas")
				&& !request.getCategoryType().toLowerCase()
						.equals("spd advance")) {
			endDate = null;
		}

		validationInputBenefitProcess(request, user);
		List<BenefitDetailDTO> listBenefitDetailDTOs = new ArrayList<BenefitDetailDTO>();
		clearBenefitDetail(request);
		Double totalHeaderAmount = Double.valueOf("0");
		Double totalHeaderAmountSubmit = Double.valueOf("0");
		if (request.getDetails() != null && request.getDetails().size() > 0) {
			for (BenefitDetailDTO benefitDetail : request.getDetails()) {

				if (benefitDetail.getType().toLowerCase().equals("cuti besar")) {
					throw new RuntimeException("This feature is not available.");
				}

				Long qty = benefitDetail.getQty();
				if (qty == null || qty == 0) {
					qty = 1L;
					if (startDate != null && endDate != null)
						qty = Utils.diffDay(startDate, endDate) + 1L;
				}

				benefitDetail.setQty(qty);
				Double totalClaim = benefitDetail.getAmount();
				Double totalClaimBalance = totalClaim;
				benefitDetail.setTotalClaim(totalClaim);

				TMBalance tmBalance = tmBalanceService.getByModuleTypeFromList(
						listBalance, request.getModule(),
						benefitDetail.getType());

				boolean passed = true;
				// Sumbangan Perabot
				if (benefitDetail.getType().toLowerCase()
						.equals("sumbangan perabot")) {
					sumbanganPerabotValidation(benefitDetail, listBalance,
							user, totalClaim);
					totalClaim = benefitDetail.getTotalClaim();
					totalClaimBalance = totalClaim;
					passed = false;
				}

				if (benefitDetail.getType().toLowerCase().equals("cuti besar")) {
					if (tmBalance != null) {
						totalClaim = tmBalance.getBalanceEnd();
						benefitDetail.setAmount(totalClaim);
						benefitDetail.setTotalClaim(totalClaim);
						totalClaimBalance = totalClaim;
					}

					passed = false;
				}

				if (benefitDetail.getType().toLowerCase().equals("taxi")
						|| benefitDetail.getType().toLowerCase()
								.equals("transport")
						|| benefitDetail.getType().toLowerCase()
								.equals("rental mobil")
						|| benefitDetail.getType().toLowerCase()
								.equals("laundry")
						|| benefitDetail.getType().toLowerCase()
								.equals("tol parkir bensin")
						|| benefitDetail.getType().toLowerCase()
								.equals("other")) {
					passed = false;
					totalClaimBalance = totalClaim;
				}

				if (tmBalance != null
						&& !tmBalance.getBalanceType().toLowerCase()
								.equals("one time (transaction)") && passed) {

					if (benefitDetail.getType().toLowerCase().equals("medical"))
						benefitDetail.setTotalSubmitedClaim(tmBalance
								.getBalanceUsed());
					else
						benefitDetail.setTotalSubmitedClaim(0D);

					totalClaimBalance = qty.doubleValue()
							* tmBalance.getBalanceEnd();
					benefitDetail
							.setLastClaimDate(tmBalance.getLastClaimDate());

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

	private void validationInputBenefitProcess(BenefitDTO request, User user) {
		boolean validationBeforeEndDate = true;
		boolean validationNextStartDate = false;
		if (request.getCategoryType().toLowerCase().equals("spd advance")) {
			validationBeforeEndDate = false;
			validationNextStartDate = true;
		}

		if (request.getCategoryType().toLowerCase().equals("perjalanan dinas")
				|| request.getCategoryType().toLowerCase()
						.equals("spd advance")) {
			List<TMRequestHeader> listHeaderRequest = tmRequestHeaderRepository
					.findBetweenStartEndDate(user.getCompany(),
							user.getEmployee(), request.getModule(),
							request.getCategoryType(), request.getStartDate());
			
			if (listHeaderRequest != null && listHeaderRequest.size() > 0) {
				throw new RuntimeException(
						"You are not allowed request in the range date.");
			}

			if (Utils.comparingDate(request.getEndDate(),
					request.getStartDate(), "<")) {
				throw new RuntimeException(
						"The End date must be greater than The start date.");
			}
		}

		if (validationBeforeEndDate) {
			//Long diffEndDate = Utils.diffDay(request.getEndDate(), new Date());
			int diffEndDate = Utils.diffDayInt(request.getEndDate(), new Date());
			if (diffEndDate < 0) {
				throw new RuntimeException(
						"Your transaction Date must be less than Today.");
			}
		}

		if (validationNextStartDate) {
			// Long diffStartDate = Utils.diffDay(request.getStartDate(), new Date());
			int diffStartDate = Utils.diffDayInt(request.getStartDate(), new Date());
			if (diffStartDate > 0) {
				throw new RuntimeException(
						"Your Start Date must be earlier than Today.");
			}
		}

		if (request.getCategoryType().toLowerCase().equals("kacamata")
				|| request.getCategoryType().toLowerCase().equals("medical")) {
			if (Utils.diffDay(request.getStartDate(), new Date()) > 30) {
				throw new RuntimeException(
						"Your transaction Date must be less than 30 days.");
			}
		}

	}

	private List<TMBalance> listBalance(List<TMBalance> resultBalance,
			Date today) {
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
	private List<TMBalance> getListBalance(BenefitDTO request, User user,
			Employment employment) {
		List<TMBalance> resultBalance = tmBalanceRepository
				.findBalanceByModuleEmployment(user.getCompany(),
						employment.getId(), request.getModule());
		Date today = new Date();
		// get all type in categoryTYpe with module BN
		List<TMBalance> listBalance = listBalance(resultBalance, today);
		return listBalance;
	}

	@Transactional
	private List<TMBalance> getListBalanceByCategory(BenefitDTO benefitDTO,
			User user, Employment employment, String category) {
		List<TMBalance> resultBalance = tmBalanceRepository
				.findBalanceCategoryTypeByEmployment(user.getCompany(),
						employment.getId(), benefitDTO.getModule(), category);
		Date today = new Date();
		List<TMBalance> listBalance = listBalance(resultBalance, today);
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
		processSubmitedClaim(request, listBalance, user);

		// move listBalance into MapBalance
		Map<String, Double> mapBalance = new HashMap<String, Double>();
		for (TMBalance tmBalance : listBalance) {
			mapBalance.put(tmBalance.getType().toLowerCase(),
					tmBalance.getBalanceEnd());
		}

		validationBenefitAmount(request, mapBalance, listBalance, user,
				employment);

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
		Double totalAmount = request.getTotal();
		Double totalAmountSubmit = request.getTotalSubmit();
		// if category Medical Overlimit get from min (balance valid Medical,
		// base salary)
		if (request.getCategoryType() != null
				&& request.getCategoryType().toLowerCase()
						.equals("medical overlimit")) {
			totalAmount = getAmountMedicalOverlimit(request, user, employment);
			totalAmountSubmit = totalAmount;
		}

		tmRequestHeader.setTotalAmount(totalAmount);
		tmRequestHeader.setTotalAmountSubmit(totalAmountSubmit);

		tmRequestHeader.setPulangKampung(request.getPulangKampung());
		tmRequestHeader.setStartDate(request.getStartDate());
		tmRequestHeader.setEndDate(request.getEndDate());
		tmRequestHeader.setSpdType(request.getSpdType());
		String reqNo = runningNumberService.getLastNumber(user.getCompany(),
				new Date(), "BN", employment.getName());
		tmRequestHeader.setReqNo(reqNo);

		if (request.getCategoryType() != null
				&& request.getCategoryType().toLowerCase()
						.equals("spd advance")) {
			tmRequestHeader.setNeedReport(true);
		}

		tmRequestHeader.setStatus(TMRequestHeader.APPROVED);
		Workflow workflow = null;
		String taskName = request.getWorkflow();
		if (tmRequestHeader.getCategoryType().toLowerCase()
				.equals("perjalanan dinas")) {
			tmRequestHeader.setSpdType(request.getSpdType());
			if (request.getSpdType() != null
					&& (request.getSpdType().toLowerCase()
							.equals("pulang kampung") || request.getSpdType()
							.toLowerCase().equals("mutasi"))) {
				if (tmRequestHeader.getTotalAmount() > BATAS_SPD_AMOUNT)
					taskName = Workflow.SUBMITBENEFIT2_1_5;
				else
					taskName = Workflow.SUBMITBENEFIT2_1;
			} else {
				if (tmRequestHeader.getTotalAmount() > BATAS_SPD_AMOUNT)
					taskName = Workflow.SUBMIT_BENEFIT2_5;
			}
			request.setWorkflow(taskName);
		}

		workflow = workflowService.findByCodeAndCompanyAndActive(taskName,
				user.getCompany(), true);

		if (workflow != null) {
			tmRequestHeader.setStatus(TMRequestHeader.PENDING);
		}

		if (request.getLinkRefHeader() != null) {
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

			if (tmRequestHeader.getCategoryType().toLowerCase()
					.equals("perjalanan dinas")) {
				tmRequest.setSpdType(tmRequestHeader.getSpdType());
			}

			tmRequest.setOrigin(request.getOrigin());
			tmRequest.setDestination(request.getDestination());

			tmRequest.setAmountSubmit(details.getTotalClaim());
			tmRequest.setAmount(details.getTotalCurrentClaim());

			if (tmRequestHeader.getCategoryType().toLowerCase()
					.equals("medical overlimit")) {
				tmRequest.setAmountSubmit(tmRequestHeader
						.getTotalAmountSubmit());
				tmRequest.setAmount(tmRequestHeader.getTotalAmount());
			}

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
			tmRequest.setReqNo(reqNo);
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

	
	private Double getAmountMedicalOverlimit(BenefitDTO benefitDTO, User user,
			Employment employment) {

		Double baseSalary = 0.0d;

		// get Base Salary
		List<PayrollElementHeader> listElementHeaders = payrollElementHeaderRepository
				.findByLatestMonthAndEmployment(employment.getId(),
						new PageRequest(0, 1));
		if (listElementHeaders != null && listElementHeaders.size() > 0) {
			PayrollElementHeader payrollHeader = listElementHeaders.get(0);
			baseSalary = payrollHeader.getBaseSalary();
		}

		// get Balance Limit Medical
		Double limitMedical = 0.0d;
		List<TMBalance> listBalance = getListBalanceByCategory(benefitDTO,
				user, employment, TMBalance.MEDICAL);
		if (listBalance != null && listBalance.size() > 0) {
			TMBalance balance = listBalance.get(0);
			limitMedical = balance.getBalanceLimit();
		}

		Double amountMedicalOverlimit = limitMedical;
		if (baseSalary != 0.0d && baseSalary < limitMedical)
			amountMedicalOverlimit = baseSalary;

		return amountMedicalOverlimit;
	}

	private void actionForBalance(List<TMBalance> listBalance,
			Map<String, Double> mapBenefitDetail, User user) {
		for (TMBalance tmBalance : listBalance) {
			if (mapBenefitDetail.get(tmBalance.getType().toLowerCase()) != null) {
				Double decrease = Double.valueOf("0");
				decrease = mapBenefitDetail.get(tmBalance.getType()
						.toLowerCase());

				if (tmBalance.getBalanceType() != null
						&& ((tmBalance.getBalanceType().toLowerCase()
								.equals("daily")
								|| tmBalance.getBalanceType().toLowerCase()
										.equals("one time (transaction)")
								|| tmBalance.getBalanceType().toLowerCase()
										.equals("one time (2 years)")
								|| tmBalance.getBalanceType().toLowerCase()
										.equals("one time (5 years)") || tmBalance
								.getBalanceType().toLowerCase()
								.equals("one time (1 years)")))) {

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
				if (benefitDetailDTO.getAmount() != null)
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

				boolean passed = true;

				if (obj.getCategoryType().toLowerCase()
						.equals("perjalanan dinas")
						|| obj.getCategoryType().toLowerCase()
								.equals("spd advance")) {
					passed = false;
				}

				if (mapBalance.get(details.getType().toLowerCase()) != null
						&& mapBalance.get(details.getType().toLowerCase()) < detailAmount
						&& passed) {
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
			Double amount = approvalWorkflow.getAmount();
			tmRequestService.approveMedicalOverlimit(headerId, listTmRequest,
					amount);
		} else {
			tmRequestService.ApproveWithHeaderId(headerId, listTmRequest);
		}

	}

	public void sumbanganPerabotValidation(BenefitDetailDTO detail,
			List<TMBalance> listBalance, User user, Double totalClaim) {
		Employee employee = employeeRepository.findOne(user.getEmployee());
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

			if (totalClaim != null) {
				if (employee.getMaritalStatus().toLowerCase().equals("single")
						|| employee.getMaritalStatus().toLowerCase()
								.equals("divorce"))
					totalClaim = TMRequest.CLAIM_PERABOT_SINGLE;
				else
					totalClaim = balance.getBalanceEnd();

				if (balance.getBalanceEnd() <= Double.valueOf(0D))
					totalClaim = 0D;

				if (totalClaim < Double.valueOf(0D))
					totalClaim = 0D;

				detail.setTotalClaim(totalClaim);
				detail.setTotalCurrentClaim(totalClaim);
				detail.setTotalSubmitedClaim(balance.getBalanceUsed());
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
							nextApply = Utils.addDay(
									balance.getLastClaimDate(), 730);
							strNextApply = Utils.convertDateToString(nextApply);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						throw new RuntimeException("You have already applied '"
								+ type + "'. You can apply again in "
								+ strNextApply + ".");
					}
				} else if (balance.getBalanceType() != null
						&& balance.getBalanceType().toLowerCase()
								.equals("one time (1 years)")) {
					// check last claim date
					if (balance.getLastClaimDate() != null
							&& (Utils.diffDay(balance.getLastClaimDate(),
									new Date()) < 365)) {
						String strNextApply = "";
						Date nextApply = null;
						try {
							nextApply = Utils.addDay(
									balance.getLastClaimDate(), 365);
							strNextApply = Utils.convertDateToString(nextApply);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						throw new RuntimeException("You have already applied '"
								+ type + "'. You can apply again in "
								+ strNextApply + ".");
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
							nextApply = Utils.addDay(
									balance.getLastClaimDate(), 1825);
							strNextApply = Utils.convertDateToString(nextApply);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						throw new RuntimeException("You have already applied '"
								+ type + "'. You can apply again in "
								+ strNextApply + ".");
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
						throw new RuntimeException("You have already applied '"
								+ type + "'. This type Only one time Apply.");

					}
				}

			}

		}
	}

	public boolean isLensaHaveClaimDate(List<TMBalance> listBalance) {
		for (TMBalance tmBalance : listBalance) {
			if (tmBalance.getType().toLowerCase().contains("lensa")
					&& tmBalance.getLastClaimDate() != null) {
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

	public void processingBeforeApproved(DataApproval dataApproval,
			ApprovalWorkflowDTO approvalWorkflowDTO) {
		TMRequestHeader objRequestHeader = tmRequestHeaderRepository
				.findOne(dataApproval.getObjectRef());
		if (objRequestHeader != null) {
			String categoryType = objRequestHeader.getCategoryType()
					.toLowerCase();
			if (categoryType.equals("medical overlimit")) {
				Double amount = approvalWorkflowDTO.getAmount();
				tmRequestRepository.updateAmountByHeaderId(amount,
						objRequestHeader.getId());
				tmRequestHeaderRepository.updateTotalAmountById(amount,
						objRequestHeader.getId());
			}
		}
	}
	
	
	@Transactional
	public void verificationAttendance(BenefitDTO request, User user, Employment employment, Employment requester){
		
		List<RequestType> listType = RequestTypeRepository.findByCompanyAndModuleAndCategoryAndType(user.getCompany(), request.getModule(), request.getCategoryType(), request.getType());
	    RequestType requestType = null;
	    if(listType != null && listType.size() > 0)
	    	requestType = listType.get(0);
	    if(requestType == null)
	    	throw new RuntimeException("Your request type is not found");
	    
		TMBalance balance = getBalance(request, user, employment);
		Double totalDay = getTotalDaysAttendance(request.getStartDate(), request.getEndDate(), employment);
		request.setTotal(totalDay);
		validationAttendanceRequest(requestType, request, balance,  user, employment);
		boolean verified = false;
		if(totalDay > 0)
			verified = true;
		
		request.setVerified(verified);
		request.setRequestType(requestType);
		if(requestType.getNeedBalance())
			request.setTotalBalance(balance.getBalanceEnd());
		
	}
	

	@Transactional
	public void createTimeAttendance(BenefitDTO request, User user, Employment employment, Employment requester){
		List<RequestCategoryType> listRequestCategory = requestCategoryTypeRepository.findByCompanyAndModuleAndCategoryType(user.getCompany(), request.getModule(),request.getCategoryType());
		RequestCategoryType requestCategoryType = null;
		if(listRequestCategory != null && listRequestCategory.size() > 0){
			requestCategoryType = listRequestCategory.get(0);
		}
		
		if(requestCategoryType == null)
		    throw new RuntimeException("Your request Category is not found");
		    
		List<RequestType> listType = RequestTypeRepository.findByCompanyAndModuleAndCategoryAndType(user.getCompany(), request.getModule(), request.getCategoryType(), request.getType());
	    RequestType requestType = null;
	    if(listType != null && listType.size() > 0)
	    	requestType = listType.get(0);
	    
	    
	    if(requestType == null)
	    	throw new RuntimeException("Your request type is not found");
	    
	    
		TMBalance balance = getBalance(request, user, employment);
		Double totalWorkDay = getTotalDaysAttendance(request.getStartDate(), request.getEndDate(), employment);
		Double totalDay = Double.valueOf(""+Utils.diffDayInt(request.getStartDate(), request.getEndDate()));
		request.setTotal(totalWorkDay);
		validationAttendanceRequest(requestType,request, balance,  user, employment);
		// 1. Validation
		
		TMRequestHeader tmRequestHeader = new TMRequestHeader();
		tmRequestHeader.setCompany(user.getCompany());
		tmRequestHeader.setCreatedBy(user.getUsername());
		tmRequestHeader.setModifiedBy(user.getUsername());
		tmRequestHeader.setModifiedDate(new Date());
		tmRequestHeader.setCreatedDate(new Date());
		tmRequestHeader.setEmployee(user.getEmployee());
		tmRequestHeader.setRequestDate(new Date());
		tmRequestHeader.setRequester(user.getEmployee());
		tmRequestHeader.setModule(TMRequestHeader.MOD_TIME_MANAGEMENT);
		tmRequestHeader.setCategoryType(request.getCategoryType());
		tmRequestHeader.setCategoryTypeExtId(request.getCategoryTypeExtId());
		tmRequestHeader.setOrigin(request.getOrigin());
		tmRequestHeader.setDestination(request.getDestination());
		tmRequestHeader.setRemark(request.getRemark());
		tmRequestHeader.setNeedReport(false);
		tmRequestHeader.setAttendanceInTime(request.getAttendanceInTime());
		tmRequestHeader.setAttendanceOutTime(request.getAttendanceOutTime());
		tmRequestHeader.setOvertimeIn(request.getOvertimeIn());
		tmRequestHeader.setOvertimeOut(request.getOvertimeOut());
		tmRequestHeader.setCategoryTypeExtId(requestCategoryType.getCategoryTypeExtId());
		Double totalAmount = totalWorkDay;
		tmRequestHeader.setTotalAmount(totalAmount);
		//Double totalAmountSubmit = request.getTotalSubmit();
		//tmRequestHeader.setTotalAmountSubmit(totalAmountSubmit);
		tmRequestHeader.setStartDate(request.getStartDate());
		tmRequestHeader.setEndDate(request.getEndDate());
		String reqNo = runningNumberService.getLastNumber(user.getCompany(),
				new Date(), TMRequestHeader.PREFIX_ATTENDANCE, employment.getName());
		tmRequestHeader.setReqNo(reqNo);
		tmRequestHeader.setStatus(TMRequestHeader.APPROVED);
		Workflow workflow = null;
		String taskName = request.getWorkflow();
		workflow = workflowService.findByCodeAndCompanyAndActive(taskName,
				user.getCompany(), true);

		if (workflow != null) {
			tmRequestHeader.setStatus(TMRequestHeader.PENDING);
		}

		tmRequestHeaderRepository.save(tmRequestHeader);
			// 2. Mapping
			TMRequest tmRequest = new TMRequest();
			tmRequest.setCompany(user.getCompany());
			tmRequest.setCreatedBy(user.getUsername());
			tmRequest.setModifiedBy(user.getUsername());
			tmRequest.setCreatedDate(new Date());
			tmRequest.setRequestDate(new Date());
			tmRequest.setRequesterEmployment(requester.getId());
			tmRequest.setStartTimeBreak(request.getStartTimeBreak());
			tmRequest.setRequesterEmploymentExtId(requester.getExtId());
			tmRequest.setEmploymentExtId(employment.getExtId());
			tmRequest.setEndTimeBreak(request.getEndTimeBreak());
			tmRequest.setRemark(request.getRemark());
			tmRequest.setModule(TMRequestHeader.MOD_TIME_MANAGEMENT);
			tmRequest.setCategoryType(request.getCategoryType());
			tmRequest.setCategoryTypeExtId(request.getCategoryTypeExtId());
			tmRequest.setType(request.getType());
			tmRequest.setStatus(TMRequest.APPROVED);
			tmRequest.setStartDate(request.getStartDate());
			tmRequest.setEmployment(employment.getId());
			tmRequest.setEmploymentExtId(employment.getExtId());
			tmRequest.setEndDate(request.getEndDate());
			tmRequest.setTotalDay(totalDay);
			tmRequest.setTmRequestHeader(tmRequestHeader.getId());
			tmRequest.setReqNo(reqNo);
			tmRequest.setNeedSync(true);
			tmRequest.setTotalWorkDay(totalWorkDay);
			tmRequest.setAttendanceInTime(request.getAttendanceInTime());
			tmRequest.setAttendanceOutTime(request.getAttendanceOutTime());
			tmRequest.setOvertimeIn(request.getOvertimeIn());
			tmRequest.setOvertimeOut(request.getOvertimeOut());
			tmRequest.setTypeDesc(request.getTypeDesc());
			tmRequest.setCategoryTypeExtId(requestCategoryType.getCategoryTypeExtId());
			if(workflow != null){
				tmRequest.setStatus(TMRequest.PENDING);
				tmRequest.setNeedSync(false);
			}
			
			tmRequestRepository.save(tmRequest);
		
		// 3. Balance
		if(requestType.getNeedBalance() && balance!= null && balance.getBalanceUsed() != null && balance.getBalanceEnd() != null){
			Double balanceEnd = balance.getBalanceEnd() - totalWorkDay;
			Double balanceUsed = balance.getBalanceUsed() + totalWorkDay;
			balance.setBalanceEnd(balanceEnd);
			balance.setBalanceUsed(balanceUsed);
			tmBalanceRepository.save(balance);
		}
		
		
		// 4. Workflow
		if(workflow != null){
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(TMRequestHeader.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(tmRequest.getId());
			dataApprovalDTO.setTask(request.getWorkflow());
			dataApprovalDTO.setModule(workflow.getModule());
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}
	
	}
	
	private void validationAttendanceRequest(RequestType requestType, BenefitDTO request, TMBalance balance,
			User user, Employment employment) {
		List<VwEmpAssignment> listVwEmpAssignments = vwEmpAssignmentRepository.findByEmployee(user.getEmployee());
		VwEmpAssignment vwEmpAssignment = null;
		if(listVwEmpAssignments != null && listVwEmpAssignments.size() > 0) 
			 vwEmpAssignment = listVwEmpAssignments.get(0);
		
		if(request.getStartDate() != null && request.getEndDate() != null){
			if(Utils.comparingDate(request.getEndDate(), request.getStartDate(), "<")){
				throw new RuntimeException("The End date must be greater than The start date.");
			}
		}
		
		// check can back date
		if(requestType.getLimitBackDate() != null && requestType.getLimitBackDate() != 0) {
			if(Utils.diffDayInt(request.getEndDate(), new Date()) > (requestType.getLimitBackDate()-1)) {
				throw new RuntimeException("Start and End date must be "+requestType.getLimitBackDate()+" days before today.");
			}
		}else {
			
			if(Utils.diffDayInt(request.getEndDate(),new Date()) > 0) {
				throw new RuntimeException("Start and End date must be earlier than today.");
			}
		}
		
		// check can future date
		if(requestType.getLimitFutureDate() != null && requestType.getLimitFutureDate() != 0) {
			if(Utils.diffDayInt(new Date(), request.getEndDate()) > (requestType.getLimitFutureDate()-1)) {
				throw new RuntimeException("Start and End date must be "+requestType.getLimitFutureDate()+" days from today.");
			}
		}else {
			// jika gak boleh future date periksa juga start date lebih dari hari ini gak 
			// jika lebih dari hari ini kasih warning
			if(Utils.diffDayInt(new Date(), request.getEndDate()) > 0) {
				throw new RuntimeException("End date must be less than today.");
			}
		}
		
		// check max num of days
		if(requestType.getMaxNumOfDays() != null && requestType.getMaxNumOfDays() != 0) {
			if(request.getTotal() > requestType.getMaxNumOfDays())
				throw new RuntimeException("Max request "+requestType.getMaxNumOfDays()+" days.");
		}
		
		if(requestType.getFlagOvertime()!= null && requestType.getFlagOvertime()) {
			EmployeePayroll employeePayroll = employeePayrollRepository.findByEmployeeNo(employment.getExtId());
			if(!employeePayroll.getFlagLembur()) {
				throw new RuntimeException("You are not allowed to request Overtime");
			}
		}
		
		if(requestType.getGender() != null && !requestType.getGender().equals("")){
			// get employee data
			Employee employee = employeeRepository.findOne(user.getEmployee());
			if(employee != null && !employee.getGender().toLowerCase().equals(requestType.getGender().toLowerCase())){
				throw new RuntimeException("Your gender is not "+requestType.getGender()+".");
			}
		}
		
		if(requestType.getGradeStart() != null && requestType.getGradeEnd() != null) {
			if(vwEmpAssignment != null) {
				if(vwEmpAssignment.getGradeNominal() < requestType.getGradeStart() || vwEmpAssignment.getGradeNominal() > requestType.getGradeEnd()){
					throw new RuntimeException(
							"Your grade is not between "+requestType.getGradeStart()+" - " + requestType.getGradeEnd());
				}
			}
			
		}
		
		if(requestType.getDefaultMaxMinutesPerMonth() != null && requestType.getDefaultMaxMinutesPerMonth() != 0) {
			// default max minutes per month for overtime 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			String month = dateFormat.format(date);
			Long totalOvertimeIn = 0L;
			Long totalOvertimeOut = 0L;
			Long totalOvertime = 0L;
			// get total minutes per month 
			TotalCategoryDTO totalCategory = null;
			
			List<TotalCategoryDTO> listTotal = tmRequestRepository.sumTotalOvertimeInOutPerMonth(user.getCompany(), employment.getId(), month, request.getModule(), requestType.getType());
			if(listTotal != null && listTotal.size() > 0){
				totalCategory = listTotal.get(0);
				if(totalCategory.getTotalOvertimeIn()!= null)
					totalOvertimeIn = totalCategory.getTotalOvertimeIn();
				
				if(totalCategory.getTotalOvertimeOut() != null)
					totalOvertimeOut = totalCategory.getTotalOvertimeOut();
			}
			
			totalOvertime = totalOvertimeIn + totalOvertimeOut + request.getOvertimeIn() + request.getOvertimeOut();
			
			String jobTitle = null;
			if(vwEmpAssignment != null)
				jobTitle = vwEmpAssignment.getJobTitleName();
			
			// check
			if(requestType.getJobTitle() != null && !requestType.getJobTitle().equals("") && jobTitle != null && requestType.getJobTitle().contains(jobTitle)) {
				if(requestType.getMaxMinutesPerMonthJobtitle() != null && requestType.getMaxMinutesPerMonthJobtitle() != 0) {
					System.out.println("pass getJobTitle");
					// if total minutes per month > request.getMaxMinutesPerMonthJobtitle(). Show warning
					if(totalOvertime > requestType.getMaxMinutesPerMonthJobtitle())
						throw new RuntimeException("Your Overtime Total has exceeded the limit this month.");
				}
			}else {
				System.out.println("not in pass getJobTitle");
				if(totalOvertime > requestType.getDefaultMaxMinutesPerMonth())
					throw new RuntimeException("Your Overtime Total has exceeded the limit this month.");
			}
			
			System.out.println("type : " + requestType.getType() + ", month : " + month+", totalOvertime : "+ totalOvertime + ", totalOvertimeIn : " + totalOvertimeIn + ", totalOvertimeOut : " + totalOvertimeOut);
			
			// if total minutes per month > request.getDefaultMaxMinutesPerMonth(). Show warning
			
			
		}
		
		if(requestType.getNeedBalance() && balance == null){
			throw new RuntimeException("Your balance is not found.");
		}
		
		
		if(requestType.getNeedBalance() &&  request.getTotal() > balance.getBalanceEnd()) {
			throw new RuntimeException("Your balance is not enought.");
		}
		
		
		List<TMRequestHeader> listHeaderRequest = tmRequestHeaderRepository.findBetweenStartEndDate(user.getCompany(),
						user.getEmployee(), TMRequestHeader.MOD_TIME_MANAGEMENT,
						request.getCategoryType(), request.getStartDate());
		
		if (listHeaderRequest != null && listHeaderRequest.size() > 0) {
			throw new RuntimeException(
					"You are not allowed request in the range date.");
		}

		
	}




	public Double getTotalDaysAttendance(Date startDate, Date endDate, Employment employment){
		Double totalDay = Double.valueOf(0);
		Double holiday = Double.valueOf(0);
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startDate);
		endCal.setTime(endDate);
		
		//startDate and endDate are the same
		if(startCal.getTimeInMillis() == endCal.getTimeInMillis())
			return Double.valueOf(1);
		
		List<Holiday> listHoliday = holidayRepository.findByCompany(employment.getCompany());
		
		EmployeePayroll employeePayroll = employeePayrollRepository.findByEmployeeNo(employment.getExtId());
		if(employeePayroll == null) {
			throw new RuntimeException(
					"Employment Payroll is not Found.");
		}
		
		Group groupAttendance = groupAttendanceRepository.findByExtId(employeePayroll.getAttendanceGroupCode());
		List<Pattern> listPattern = patternRepository.findByLookupField(groupAttendance.getPatternCode());
		List<AttendanceRefDTO> arrAttendanceRefDTO = getAttendanceRef(startDate, endDate,groupAttendance,listPattern);
		List<Shift> listShift = shiftRepository.findByCompany(employment.getCompany());
		
		do{
			
			
			if(!isOffDay(startCal,arrAttendanceRefDTO,listPattern,listShift, listHoliday))
				totalDay++;
			
			startCal.add(Calendar.DAY_OF_MONTH, 1); // startCal.add(Calendar.DATE, 1);
			
		}while(startCal.getTimeInMillis() <= endCal.getTimeInMillis());	
		
		totalDay = totalDay - holiday;
		return totalDay;
	}
	
private boolean isOffDay(Calendar startCal,List<AttendanceRefDTO> arrAttendanceRefDTO,List<Pattern> listPattern, List<Shift> listShift, List<Holiday> listHoliday) {
		
		for(int i = 1; i < arrAttendanceRefDTO.size(); i++){
			Date date = startCal.getTime();
			String formattedPanduanDate = dateFormat.format(arrAttendanceRefDTO.get(i).getDate());
			String formattedDate = dateFormat.format(date);
			Pattern objPatternDTO = listPattern.get(arrAttendanceRefDTO.get(i).getSequencePattern() - 1);
			
			// check is Holiday
			if(isHoliday(startCal, listHoliday))
				return true;
				
			if(formattedPanduanDate.equals(formattedDate)){
				Shift objShift = getShiftFromPattern(objPatternDTO.getShiftCode(), listShift);
				
				if(objShift != null && objShift.getMasterCode() !=null && objShift.getMasterCode().toLowerCase().contains("off"))
					return true;
			}
		}
		
		return false;
}

private boolean isHoliday(Calendar currentDate,List<Holiday> listHoliday){
	
	if(listHoliday != null && listHoliday.size() > 0){
		for (Holiday holiday : listHoliday) {
			Calendar calHoliday = Calendar.getInstance();
			calHoliday.setTime(holiday.getDate());
			
			if (Utils.isSameDay(currentDate, calHoliday))
				return true;
		
		}
	}
	
	return false;
}

private Shift getShiftFromPattern(String shiftCode, List<Shift> listShift){
	for (Shift shift : listShift) {
		if(shift.getExtId().equals(shiftCode))
			return shift;
	}
	return null;
}

private Pattern getPatternByPosition(int position, List<Pattern> listPattern){
	for (Pattern pattern : listPattern) {
		if(pattern.getPatternNo() == position)
			return pattern;
	}
	return null;
}

public List<AttendanceRefDTO> getAttendanceRef(Date startDate, Date endDate, Group groupAttendance, List<Pattern> listPattern){
	Calendar startCal = Calendar.getInstance();
	Calendar endCal = Calendar.getInstance();
	startCal.setTime(startDate);
	endCal.setTime(endDate);
	
	
	List<AttendanceRefDTO> arrAttendanceRef = new ArrayList<AttendanceRefDTO>();
	AttendanceRefDTO initAttendanceRefDTO = new AttendanceRefDTO();
	initAttendanceRefDTO.setDate(groupAttendance.getStartDate());
	initAttendanceRefDTO.setSequenceDay(groupAttendance.getSequenceDayNo());
	initAttendanceRefDTO.setSequencePattern(groupAttendance.getPatternNo());
	
	arrAttendanceRef.add(initAttendanceRefDTO);
	
	Calendar calPanduan = Calendar.getInstance();
	calPanduan.setTime(arrAttendanceRef.get(0).getDate());
	
	
	Date date = null;
	int orderPattern = 0;
	int sequenceDay = 0;
	
	for(int i = 1; i <= Utils.diffDay(arrAttendanceRef.get(0).getDate(), endDate); i++){
		//date
		calPanduan.setTime(arrAttendanceRef.get(i-1).getDate());
		calPanduan.add(Calendar.DAY_OF_MONTH, 1);
		date = calPanduan.getTime();
		
		//position
		sequenceDay = arrAttendanceRef.get(i-1).getSequenceDay() + 1;
		
		//pattern / order
		int sequencePattern = arrAttendanceRef.get(i-1).getSequencePattern() - 1;
		Pattern objPattern = getPatternByPosition(arrAttendanceRef.get(i-1).getSequencePattern(),listPattern);
		
		//if(sequenceDay > listPattern.get(sequencePattern).getNoDays()){
		if(sequenceDay > objPattern.getNoDays()){
			orderPattern = arrAttendanceRef.get(i-1).getSequencePattern() + 1;
			Pattern newObjPattern =  getPatternByPosition(arrAttendanceRef.get(i-1).getSequencePattern()+1,listPattern);
			if(newObjPattern == null)
				orderPattern =1;
			else
				orderPattern = newObjPattern.getPatternNo();
			
//			if(orderPattern > 6) // 6 dari mana ???
//				orderPattern = 1;
			sequenceDay = 1;
		}else{
			orderPattern = arrAttendanceRef.get(i-1).getSequencePattern();
		}

		AttendanceRefDTO attendanceRefDTO = new AttendanceRefDTO();
		attendanceRefDTO.setDate(date);
		attendanceRefDTO.setSequenceDay(sequenceDay);
		attendanceRefDTO.setSequencePattern(orderPattern);
		arrAttendanceRef.add(attendanceRefDTO);
	}
	
	return arrAttendanceRef;
}


	
	
	
	@Transactional
	private TMBalance getBalance(BenefitDTO request, User user,
			Employment employment) {
		Date today = new Date();
		TMBalance balance =null;
		List<TMBalance> resultBalance = tmBalanceRepository.findBalanceTypeByEmployment(user.getCompany(),employment.getId(),request.getModule(),request.getCategoryType(),request.getType(),today);
		if(resultBalance!= null && resultBalance.size() > 0)
			balance = resultBalance.get(0);
		return balance;
	}
}
