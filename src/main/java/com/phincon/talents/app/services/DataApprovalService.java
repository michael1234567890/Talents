package com.phincon.talents.app.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.async.SendingEmailService;
import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.AddressTempRepository;
import com.phincon.talents.app.dao.DataApprovalHistoryRepository;
import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.TemplateMailRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.DataApprovalHistory;
import com.phincon.talents.app.model.SendingEmail;
import com.phincon.talents.app.model.TemplateMail;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.AddressTemp;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.FamilyTemp;
import com.phincon.talents.app.model.hr.LeaveRequest;
import com.phincon.talents.app.model.hr.TMRequestHeader;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.utils.Utils;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class DataApprovalService {

	@Autowired
	DataApprovalRepository dataApprovalRepository;
	@Autowired
	SendingEmailService sendingEmailService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	FamilyService familyService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DataApprovalHistoryRepository dataApprovalHistoryRepository;
	
	
	@Autowired
	VwEmpAssignmentRepository vwEmpAssignmentRepository;

	@Autowired
	FamilyTempService familyTempService;

	@Autowired
	TemplateMailRepository templateMailRepository;

	@Autowired
	TMRequestHeaderService tmRequestHeaderService;

	@Autowired
	AddressService addressService;

	@Autowired
	AddressTempRepository addressTempRepository;

	@Autowired
	LeaveRequestService leaveRequestService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AttachmentDataApprovalService attachmentDataApprovalService;

	@Autowired
	private Environment env;

	@Transactional
	public DataApproval findById(Long id,User user, boolean bypass) {
		
		DataApproval dataApproval = null; 
		if(bypass) {
			dataApproval = dataApprovalRepository.findOne(id);
		}else {
			dataApproval = dataApprovalRepository.findByIdAndEmpRequest(id, user.getEmployee());
		}
		
		
		if (dataApproval != null) {
			if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)
					|| dataApproval.getTask().equals(Workflow.CHANGE_FAMILY)) {
				if (dataApproval.getProcessingStatus().equals(
						DataApproval.PROC_STATUS_REJECT)) {
					System.out.println("Data Approval Object Ref "
							+ dataApproval.getObjectRef());
					FamilyTemp familyTemp = familyTempService
							.findById(dataApproval.getObjectRef());
					dataApproval.setRef(familyTemp);
				} else {
					Family family = familyService.findById(dataApproval
							.getObjectRef());
					dataApproval.setRef(family);
				}

			} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
				LeaveRequest obj = leaveRequestService.findById(dataApproval
						.getObjectRef());
				Object objConvert = (Object) obj;
				dataApproval.setRef(objConvert);
			} else if (dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)
					|| dataApproval.getTask().equals(Workflow.CHANGE_ADDRESS)) {
				if (dataApproval.getProcessingStatus().equals(
						DataApproval.PROC_STATUS_REJECT)) {
					AddressTemp addressTemp = addressTempRepository
							.findOne(dataApproval.getObjectRef());
					dataApproval.setRef(addressTemp);
				} else {
					Address obj = addressService.findById(dataApproval
							.getObjectRef());
					Object objConvert = (Object) obj;
					dataApproval.setRef(objConvert);
				}

			} else if (dataApproval.getTask().contains(Workflow.SUBMIT_BENEFIT) || dataApproval.getTask().contains(Workflow.SUBMIT_ATTENDANCE)) {
				TMRequestHeader tmRequestHeader = tmRequestHeaderService
						.findByIdWithDetail(dataApproval.getObjectRef());

				if (tmRequestHeader.getRefRequestHeader() != null) {
					TMRequestHeader tmRequestHeaderRef = tmRequestHeaderService
							.findById(tmRequestHeader.getRefRequestHeader());
					tmRequestHeader.setRefRequestHeaderObj(tmRequestHeaderRef);
				}
				dataApproval.setRef(tmRequestHeader);

			}

			Employee objEmployee = employeeService
					.findEmployeeWithAssignment(dataApproval.getEmpRequest());
			dataApproval.setEmployeeRequest(objEmployee);
		}

		return dataApproval;
	}

	@Transactional
	public Iterable<DataApproval> findAll() {
		return dataApprovalRepository.findAll();
	}

	private List<DataApproval> modifyResultDataApproval(
			List<DataApproval> listDataApprovals, HttpServletRequest request) {
		List<DataApproval> lisApprovalsTemp = new ArrayList<DataApproval>();
		if (listDataApprovals != null && listDataApprovals.size() > 0) {
			for (DataApproval dataApproval : listDataApprovals) {
				Employee objEmployee = employeeService
						.findEmployee(dataApproval.getEmpRequest());
				dataApproval.setEmployeeRequest(objEmployee);
				if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
					LeaveRequest obj = leaveRequestService
							.findById(dataApproval.getObjectRef());
					Object objConvert = (Object) obj;
					dataApproval.setRef(objConvert);
				} else if (dataApproval.getTask().contains(
						Workflow.SUBMIT_BENEFIT) || dataApproval.getTask().contains(Workflow.SUBMIT_ATTENDANCE)) {
					TMRequestHeader obj = tmRequestHeaderService
							.findById(dataApproval.getObjectRef());
					Object objConvert = (Object) obj;
					dataApproval.setRef(objConvert);
				} 	

				// User
				User userEmployee = userRepository.findByEmployee(dataApproval
						.getEmpRequest());
				// ambil foto profile
				String image = null;
				if (userEmployee != null
						&& userEmployee.getPhotoProfile() != null
						&& !userEmployee.getPhotoProfile().equals("")) {
					
					String http = env.getProperty("talents.protocol");
					image = Utils.getUrlAttachment(http, request,
							userEmployee.getPhotoProfile());
					
					
				}

				dataApproval.setEmployeeRequestPhotoProfile(image);
				lisApprovalsTemp.add(dataApproval);
			}
		}
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByEmployee(Long empRequest,
			HttpServletRequest request, PageRequest pageRequest) {
		List<DataApproval> listDataApprovals = dataApprovalRepository
				.findByEmpRequest(empRequest, pageRequest);
		List<DataApproval> lisApprovalsTemp = modifyResultDataApproval(
				listDataApprovals, request);
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByEmployeeAndModule(Long empRequest,
			String module, HttpServletRequest request, PageRequest pageRequest) {
		List<DataApproval> listDataApprovals = dataApprovalRepository
				.findByEmpRequestAndModule(empRequest, module, pageRequest);
		List<DataApproval> lisApprovalsTemp = modifyResultDataApproval(
				listDataApprovals, request);
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByCompanyAndObjectNameAndObjectRef(
			Long company, String objectRefName, Long objectRef,
			HttpServletRequest request) {
		List<DataApproval> listDataApproval = dataApprovalRepository
				.findByCompanyAndObjectNameAndObjectRef(company, objectRefName,
						objectRef);

		if (listDataApproval != null && listDataApproval.size() > 0) {
			for (DataApproval dataApproval : listDataApproval) {
				Employee objEmployee = employeeService
						.findEmployee(dataApproval.getEmpRequest());
				dataApproval.setEmployeeRequest(objEmployee);

				// User
				User userEmployee = userRepository.findByEmployee(dataApproval
						.getEmpRequest());
				String image = null;
				if (userEmployee != null
						&& userEmployee.getPhotoProfile() != null
						&& !userEmployee.getPhotoProfile().equals("")) {
					String http = env.getProperty("talents.protocol");
					image = Utils.getUrlAttachment(http, request,
							userEmployee.getPhotoProfile());
					
					
				}

				dataApproval.setEmployeeRequestPhotoProfile(image);
			}
		}

		return listDataApproval;
	}

	@Transactional
	public List<DataApproval> findNeedApproval(String emp, String status,
			Long company, String module, PageRequest pageRequest,
			HttpServletRequest request) {
		List<DataApproval> listDataApprovals = null;
		if (module == null)
			listDataApprovals = dataApprovalRepository.findNeedApproval(emp,
					status, company);
		else
			listDataApprovals = dataApprovalRepository
					.findNeedApprovalAndModule(emp, status, company, module,
							pageRequest);

		List<DataApproval> lisApprovalsTemp = modifyResultDataApproval(
				listDataApprovals, request);
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByEmployeeAndTaskAndActiveAndStatus(
			Long employee, String task, boolean active, String status) {
		return dataApprovalRepository
				.findByEmpRequestAndTaskAndActiveAndStatus(employee, task,
						active, status);
	}

	@Transactional
	public void save(DataApproval obj) {
		dataApprovalRepository.save(obj);
	}

	@Transactional
	public void save(DataApprovalDTO request, User user, Workflow workflow) {
		DataApproval dataApproval = null;
		boolean isDataExist = false;
		int currentApprovalLevel = 0;
		int approvalLevel = 1;
		String approvalLevelOne = null;
		String approvalLevelTwo = null;
		String approvalLevelThree = null;

		String approvalLevelFour = null;
		String approvalLevelFive = null;
		String approvalLevelSix = null;

		String approvalLevelSeven = null;
		String currentAssignApproval = null;

		validationDataApproval(request, user);

		if (workflow.getOperation() != null && workflow.getOperation().equals(Workflow.OPERATION_EDIT)) {
			if (request.getIdRef() == null) {
				throw new CustomException(
						"Error : your ref id can be empty. your task "
								+ request.getTask() + " is "
								+ Workflow.OPERATION_EDIT);
			}
		}

		approvalLevelOne = workflowService.findAssignApproval(workflow.getApprovalCodeLevelOne(), user.getEmployee(),user.getCompany(), request.getTask());
		
		if (workflow.getApprovalCodeLevelTwo() != null&& (!workflow.getApprovalCodeLevelTwo().equals("") || !workflow.getApprovalCodeLevelTwo().isEmpty())) {
			approvalLevel = 2;
			approvalLevelTwo = workflowService.findAssignApproval(workflow.getApprovalCodeLevelTwo(), user.getEmployee(),user.getCompany(), request.getTask());
		}

		if (workflow.getApprovalCodeLevelThree() != null && !workflow.getApprovalCodeLevelThree().equals("")) {
			approvalLevel = 3;
			approvalLevelThree = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelThree(), user.getEmployee(),
					user.getCompany(), request.getTask());
		}

		if (workflow.getApprovalCodeLevelFour() != null && !workflow.getApprovalCodeLevelFour().equals("")) {
			approvalLevel = 4;
			approvalLevelFour = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelFour(), user.getEmployee(),
					user.getCompany(), request.getTask());
		}

		if (workflow.getApprovalCodeLevelFive() != null&& !workflow.getApprovalCodeLevelFive().equals("")) {
			approvalLevel = 5;
			approvalLevelFive = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelFive(), user.getEmployee(),
					user.getCompany(), request.getTask());
		}

		if (workflow.getApprovalCodeLevelSix() != null&& !workflow.getApprovalCodeLevelSix().equals("")) {
			approvalLevel = 6;
			approvalLevelSix = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelSix(), user.getEmployee(),
					user.getCompany(), request.getTask());
		}

		if (workflow.getApprovalCodeLevelSeven() != null&& !workflow.getApprovalCodeLevelSeven().equals("")) {
			approvalLevel = 7;
			approvalLevelSeven = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelSeven(), user.getEmployee(),
					user.getCompany(), request.getTask());
		}

		if (workflow.getOperation() != null&& workflow.getOperation().equals(Workflow.OPERATION_EDIT)) {
			List<DataApproval> listDataApproval = dataApprovalRepository.findByEmpRequestAndTaskAndActiveAndStatus(user.getEmployee(), request.getTask(), true,DataApproval.NOT_COMPLETED);

			if (listDataApproval != null && listDataApproval.size() > 0) {
				dataApproval = listDataApproval.get(0);
				if (dataApproval.getCurrentApprovalLevel() > 0) {
					throw new CustomException(
							"Error : Your request is on progress. Please wait while we process your request");
				}
				dataApproval.setData(request.getData());
				dataApproval.setModifiedBy(user.getUsername());
				dataApproval.setModifiedDate(new Date());
				dataApproval.setObjectRef(request.getIdRef());
				isDataExist = true;
			}
		}

		if (!isDataExist) {
			dataApproval = new DataApproval();
			dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_REQUEST);
			dataApproval.setData(request.getData());
			dataApproval.setActive(true);
			dataApproval.setObjectRef(request.getIdRef());
			dataApproval.setTask(request.getTask());
			dataApproval.setModule(request.getModule());
			dataApproval.setEmpRequest(user.getEmployee());
			dataApproval.setCompany(user.getCompany());
			dataApproval.setCreatedDate(new Date());
			dataApproval.setModifiedDate(new Date());
			dataApproval.setObjectRef(request.getIdRef());
			dataApproval.setStatus(DataApproval.NOT_COMPLETED);
			dataApproval.setApprovalLevelOne(approvalLevelOne);
			dataApproval.setApprovalLevelTwo(approvalLevelTwo);
			dataApproval.setApprovalLevelThree(approvalLevelThree);
			dataApproval.setApprovalLevelFour(approvalLevelFour);
			dataApproval.setApprovalLevelFive(approvalLevelFive);
			dataApproval.setApprovalLevelSix(approvalLevelSix);
			dataApproval.setApprovalLevelSeven(approvalLevelSeven);
			
			if(approvalLevelOne != null){
				currentAssignApproval = approvalLevelOne;
			}else {
				if(approvalLevelTwo != null) {
					currentAssignApproval = approvalLevelTwo;
				}else {
					dataApproval.setStatus(DataApproval.COMPLETED);
				}
				currentApprovalLevel++;
			}
				
			
			
			dataApproval.setCurrentAssignApproval(currentAssignApproval);
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			dataApproval.setApprovalLevel(approvalLevel);
			dataApproval.setCreatedBy(user.getUsername());
			dataApproval.setModifiedBy(user.getUsername());
			dataApproval.setObjectName(request.getObjectName());
			dataApproval.setDescription(request.getDescription());
		}

		if (request.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			dataApproval.setDescription(workflow.getDescription());
			dataApproval.setObjectName(Employee.class.getSimpleName());
		}

		dataApproval.setModule(workflow.getModule());
		save(dataApproval);

		if (request.getAttachments() != null&& request.getAttachments().size() > 0) {

			// save attachment`
			for (Map<String, Object> map : request.getAttachments()) {
				String imageBase64 = (String) map.get("image");
				if (imageBase64 != null) {
					String pathname = "workflow/"+ RandomStringUtils.randomAlphanumeric(10) + "."+ Utils.UPLOAD_IMAGE_TYPE;
					Utils.createImage(imageBase64, pathname);
					AttachmentDataApproval obj = new AttachmentDataApproval();
					obj.setPath(pathname);
					obj.setDataApproval(dataApproval.getId());
					attachmentDataApprovalService.save(obj);
				}

			}

		}

		if (request.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.requestMaritalStatus(dataApproval);
		}
		
		if(request.getTask().contains(Workflow.SUBMIT_BENEFIT)) {
			// Send Email
			String empIdString = Utils.changeTagar(currentAssignApproval);
			sendingEmail(user.getCompany(), "RESULTAPPBENEFIT",null,null, empIdString, dataApproval);
		}
		
		String descriptionHistory = "Request created by " + user.getUsername();
		saveWorkflowHistory(dataApproval,user,descriptionHistory,"Request");

	}

	private void saveWorkflowHistory(DataApproval dataApproval,User user, String description, String status) {
		DataApprovalHistory dataApprovalHist = new DataApprovalHistory();
		dataApprovalHist.setCreatedDate(new Date());
		dataApprovalHist.setModifiedBy(user.getUsername());
		dataApprovalHist.setModifiedDate(new Date());
		dataApprovalHist.setDataApproval(dataApproval.getId());
		dataApprovalHist.setCreatedBy(user.getUsername());
		dataApprovalHist.setCurrentApproval(dataApproval.getCurrentApprovalLevel());
		dataApprovalHist.setCompany(user.getCompany());
		dataApprovalHist.setEmployeeApproval(user.getEmployee());
		dataApprovalHist.setDescription(description);
		dataApprovalHist.setStatus(status);
		dataApprovalHistoryRepository.save(dataApprovalHist);
	}

	private void validationDataApproval(DataApprovalDTO request, User user) {
		if (request.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			Employee employee = employeeRepository.findOne(user.getEmployee());
			if (employee.getMaritalStatus() != null) {
				Map<String, Object> paramsMap = Utils
						.convertStrJsonToMap(request.getData());
				if (paramsMap == null) {
					throw new CustomException("Problem with convert Data");
				}

				String changeMaritalStatus = (String) paramsMap
						.get("maritalStatus");
				changeMaritalStatus = changeMaritalStatus.toLowerCase();
				String currentMaritalStatus = employee.getMaritalStatus()
						.toLowerCase();
				if (changeMaritalStatus.equals(currentMaritalStatus)) {
					throw new CustomException(
							"Your new marital status is same  with the current one");
				}

				if (currentMaritalStatus.equals("single")
						&& changeMaritalStatus.equals("divorce")) {
					throw new CustomException(
							"You can't change your status from Single to Divorce");
				}

				if (currentMaritalStatus.equals("married")
						&& changeMaritalStatus.equals("single")) {
					throw new CustomException(
							"You can't change your status from Married to Single");
				}

				if (currentMaritalStatus.equals("divorce")
						&& changeMaritalStatus.equals("single")) {
					throw new CustomException(
							"You can't change your status from Divorce to Single");
				}

			}
		}
	}

	@Transactional
	public void approval(ApprovalWorkflowDTO approvalWorkflow, User user,
			boolean isBypass) {
		DataApproval dataApproval = dataApprovalRepository
				.findOne(approvalWorkflow.getId());
		boolean isSendToRequest = false;
		if (dataApproval == null) {
			throw new CustomException("Data Approval is not found");
		}

		if (dataApproval.getStatus().equals(DataApproval.COMPLETED) && !isBypass) {
			throw new CustomException("This request is already Completed");
		}

		String strEmployee = "#" + user.getEmployee() + "#";

		// cek dulu user punya akses approval gak
		if( !dataApproval.getEmpRequest().equals(user.getEmployee())){
			if (!dataApproval.getCurrentAssignApproval().contains(strEmployee) && !isBypass  ) {
				throw new CustomException(
						"You don't have permission to approve this request");
			}
		}
		

		if (approvalWorkflow.getStatus().equals(DataApproval.APPROVED)) {

			// jika berhasil naikan current Approval level data approval
			Integer currentApprovalLevel = dataApproval
					.getCurrentApprovalLevel() + 1;

			if (currentApprovalLevel > dataApproval.getApprovalLevel()) {
				throw new CustomException("Current approval step is over");
			}
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			
			if (dataApproval.getApprovalLevel() == currentApprovalLevel) {
				isSendToRequest = true;
				dataApproval.setStatus(DataApproval.COMPLETED);
				dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_APPROVE);
				callingApprovedMethod(dataApproval, approvalWorkflow);

			} else {
				Integer nextLevelApproval = dataApproval.getCurrentApprovalLevel() + 1;
				if (nextLevelApproval == 2 && dataApproval.getApprovalLevelTwo() != null && !dataApproval.getApprovalLevelTwo().isEmpty()) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelTwo());
				} else if (nextLevelApproval == 3 && !dataApproval.getApprovalLevelThree().isEmpty() && dataApproval.getApprovalLevelThree() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelThree());
				} else if (nextLevelApproval == 4 && !dataApproval.getApprovalLevelFour().isEmpty() && dataApproval.getApprovalLevelFour() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelFour());
				} else if (nextLevelApproval == 5 && !dataApproval.getApprovalLevelFive().isEmpty() && dataApproval.getApprovalLevelFive() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelFive());
				} else if (nextLevelApproval == 6 && !dataApproval.getApprovalLevelSix().isEmpty() && dataApproval.getApprovalLevelSix() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelSix());
				} else if (nextLevelApproval == 7 && !dataApproval.getApprovalLevelSeven().isEmpty() && dataApproval.getApprovalLevelSeven() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval.getApprovalLevelSeven());
				}

				processingBeforeApprovedObject(dataApproval, approvalWorkflow);
				dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_WAITING);
				// Send Email

			}

		} else if (approvalWorkflow.getStatus().equals(DataApproval.REJECTED)) {
			isSendToRequest = true;
			dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_REJECT);
			dataApproval.setStatus(DataApproval.COMPLETED);
			dataApproval.setReasonReject(approvalWorkflow.getReasonReject());
			String fullName = user.getFirstName();
			
			if (user.getLastName() != null)
				fullName += " " + user.getLastName();
			
			dataApproval.setRejectedBy(fullName);
			callingRejectCancel(dataApproval,DataApproval.REJECTED);
		} else if (approvalWorkflow.getStatus().equals(DataApproval.CANCELLED)) {
			isSendToRequest = true;
			dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_CANCEL);
			dataApproval.setStatus(DataApproval.COMPLETED);
			String fullName = user.getFirstName();
			
			if (user.getLastName() != null)
				fullName += " " + user.getLastName();
			
			dataApproval.setRejectedBy(fullName);
			callingRejectCancel(dataApproval,DataApproval.CANCELLED);
		}else {
			throw new CustomException(
					"Your action approval is undefined");
		}

		dataApprovalRepository.save(dataApproval);
		
		// prepare for create Email
		String empIdString = null;
		if (isSendToRequest)
			empIdString = "" + dataApproval.getEmpRequest();
		else
			empIdString = Utils.changeTagar(dataApproval
					.getCurrentAssignApproval());

		sendingEmail(user.getCompany(), "RESULTAPPBENEFIT",null,null, empIdString, dataApproval);
		
		
		String descriptionHistory = "Request " + approvalWorkflow.getStatus() + " by " + user.getUsername();
		saveWorkflowHistory(dataApproval,user,descriptionHistory,approvalWorkflow.getStatus());
		
	}

	private void processingBeforeApprovedObject(DataApproval dataApproval,
			ApprovalWorkflowDTO approvalWorkflowDTO) {
		if (dataApproval.getObjectName().equals(
				TMRequestHeader.class.getSimpleName())) {
			tmRequestHeaderService.processingBeforeApproved(dataApproval,
					approvalWorkflowDTO);
		}
	}

	private void callingRejectCancel(DataApproval dataApproval, String status) {
		if (dataApproval.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.rejectedChangeMaritalStatus(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
			leaveRequestService.rejected(dataApproval, LeaveRequest.REJECTED);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)) {
			familyService.rejected(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)) {
			addressService.rejected(dataApproval);
		} else if (dataApproval.getTask().contains(Workflow.SUBMIT_BENEFIT) || dataApproval.getTask().contains(Workflow.SUBMIT_ATTENDANCE)) {
			tmRequestHeaderService.rejectCancel(dataApproval,status);
		} else if (dataApproval.getTask().equals(Workflow.CHANGE_FAMILY)) {
			familyService.rejectedChange(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.CHANGE_ADDRESS)) {
			addressService.rejectedChange(dataApproval);
		}
	}

	private void callingApprovedMethod(DataApproval dataApproval,
			ApprovalWorkflowDTO approvalWorkflow) {
		if (dataApproval.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.approvedChangeMaritalStatus(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
			leaveRequestService.approved(dataApproval, LeaveRequest.APPROVED);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)) {
			familyService.approvedSubmitFamily(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)) {
			addressService.approvedSubmitAddress(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.CHANGE_FAMILY)) {
			familyService.approvedChangeFamily(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.CHANGE_ADDRESS)) {
			addressService.approvedChangeAddress(dataApproval);
		} else if (dataApproval.getTask().contains(Workflow.SUBMIT_BENEFIT) || dataApproval.getTask().contains(Workflow.SUBMIT_ATTENDANCE)) {
			tmRequestHeaderService.approved(dataApproval, approvalWorkflow);

		}

	}

	

	private void sendingEmail(Long company, String templateCode, String data,
			String emailTo, String listEmpId, DataApproval dataApproval) {
		// sending email
		if (dataApproval.getTask().contains(Workflow.SUBMIT_BENEFIT)) {
			
			if (dataApproval.getStatus().equals(DataApproval.COMPLETED))
				templateCode = TemplateMail.RESULTAPPBENEFIT;
			else
				templateCode = TemplateMail.APPBENEFIT;
			
			Long empId = dataApproval.getEmpRequest();
			List<VwEmpAssignment> myAssignmentList = vwEmpAssignmentRepository.findByEmployee(
					empId);
			
			VwEmpAssignment empRequestAssignment = null;
			if(myAssignmentList != null && myAssignmentList.size() > 0)
				empRequestAssignment = myAssignmentList.get(0);
			
			Long benefitId = dataApproval.getObjectRef();
			TMRequestHeader obj = tmRequestHeaderService.findById(benefitId);
			try {
				SendingEmail objEmail = new SendingEmail();
				objEmail.setListEmpId(listEmpId);
				objEmail.setCodeTemplate(templateCode);
				objEmail.setCompany(company);
				List<TemplateMail> listTemplateMail = templateMailRepository
						.findByCompanyAndCode(company, templateCode);
				TemplateMail templateMail;
				if (listTemplateMail != null && listTemplateMail.size() > 0) {
					templateMail = listTemplateMail.get(0);
					// Map<String, Object> mapData = Utils.stringToMap(data);
					Map<String, Object> mapData = new HashMap<String, Object>();
					
					mapData.put("reqNo", obj.getReqNo());
					mapData.put("category", obj.getCategoryType());
					DecimalFormat decimalFormat = new DecimalFormat("#.00");
				    decimalFormat.setGroupingUsed(true);
				    decimalFormat.setGroupingSize(3);

					mapData.put("totalAmount", decimalFormat.format(obj.getTotalAmount()));
					if(obj.getCategoryType().toLowerCase().equals("medical overlimit"))
						mapData.put("totalAmount", "-");
					
					mapData.put("requestDate",""+obj.getRequestDate());
					if(empRequestAssignment != null ) {
						mapData.put("empName",empRequestAssignment.getFullName());
						mapData.put("empNo", empRequestAssignment.getEmployeeNo());
					}
					
					String dataContent = templateMail.getContent();
					for (Map.Entry<String, Object> entry : mapData.entrySet()) {
						String key = entry.getKey();
						String value = (String) entry.getValue();
						String findKey = "{" + key + "}";
						dataContent = dataContent.replace(findKey, value);
					}
					
					String subjectMail = templateMail.getSubject();
					subjectMail = subjectMail.replace("{reqNo}",obj.getReqNo());
					objEmail.setSubject(subjectMail);
					objEmail.setDataContent(dataContent);
					objEmail.setEmailTo(emailTo);
					objEmail.setDate(new Date());
					Future future = sendingEmailService.sendMail(objEmail);
					Boolean result = (Boolean) future.get();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}
