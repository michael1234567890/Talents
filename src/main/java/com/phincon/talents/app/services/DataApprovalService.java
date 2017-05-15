package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.LeaveRequest;
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
	WorkflowService workflowService;

	@Autowired
	FamilyService familyService;
	
	@Autowired
	AddressService addressService;

	@Autowired
	LeaveRequestService leaveRequestService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AttachmentDataApprovalService attachmentDataApprovalService;

	@Transactional
	public DataApproval findById(Long id) {
		DataApproval dataApproval = dataApprovalRepository.findOne(id);
		if (dataApproval != null) {
			if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)) {
				Family family = familyService.findById(dataApproval
						.getObjectRef());
				dataApproval.setRef(family);
			} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
				LeaveRequest obj = leaveRequestService.findById(dataApproval
						.getObjectRef());
				Object objConvert = (Object) obj;
				dataApproval.setRef(objConvert);
			} else if (dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)) {
				Address obj = addressService.findById(dataApproval
						.getObjectRef());
				Object objConvert = (Object) obj;
				dataApproval.setRef(objConvert);
			}
//			Employee objEmployee = employeeService.findEmployee(dataApproval
//					.getEmpRequest());
			Employee objEmployee = employeeService.findEmployeeWithAssignment(dataApproval.getEmpRequest());
			dataApproval.setEmployeeRequest(objEmployee);
		}

		return dataApproval;
	}

	@Transactional
	public Iterable<DataApproval> findAll() {
		return dataApprovalRepository.findAll();
	}

	private List<DataApproval> modifyResultDataApproval(
			List<DataApproval> listDataApprovals) {
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
				}
				lisApprovalsTemp.add(dataApproval);
			}
		}
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByEmployee(Long empRequest) {
		List<DataApproval> listDataApprovals = dataApprovalRepository
				.findByEmpRequest(empRequest);
		List<DataApproval> lisApprovalsTemp = modifyResultDataApproval(listDataApprovals);
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findNeedApproval(String emp, String status,
			Long company) {
		List<DataApproval> listDataApprovals = dataApprovalRepository
				.findNeedApproval(emp, status, company);
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
				}
				lisApprovalsTemp.add(dataApproval);
			}
		}
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
		String currentAssignApproval = null;

		if (workflow.getOperation() != null
				&& workflow.getOperation().equals(Workflow.OPERATION_EDIT)) {
			if (request.getIdRef() == null) {
				throw new RuntimeException(
						"Error : your ref id can be empty. your task "
								+ request.getTask() + " is "
								+ Workflow.OPERATION_EDIT);
			}
		}

		approvalLevelOne = workflowService.findAssignApproval(
				workflow.getApprovalCodeLevelOne(), user.getEmployee(),
				user.getCompany());

		if (workflow.getApprovalCodeLevelTwo() != null
				&& (!workflow.getApprovalCodeLevelTwo().equals("") || !workflow
						.getApprovalCodeLevelTwo().isEmpty())) {
			approvalLevel = 2;
			System.out.println("Code Approval Lev 2 "
					+ workflow.getApprovalCodeLevelTwo());
			approvalLevelTwo = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelTwo(), user.getEmployee(),
					user.getCompany());
		}

		if (workflow.getApprovalCodeLevelThree() != null
				&& !workflow.getApprovalCodeLevelThree().equals("")) {
			approvalLevel = 3;
			approvalLevelThree = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelTwo(), user.getEmployee(),
					user.getCompany());
		}

		if (workflow.getOperation() != null
				&& workflow.getOperation().equals(Workflow.OPERATION_EDIT)) {
			List<DataApproval> listDataApproval = dataApprovalRepository
					.findByEmpRequestAndTaskAndActiveAndStatus(
							user.getEmployee(), request.getTask(), true,
							DataApproval.NOT_COMPLETED);

			if (listDataApproval != null && listDataApproval.size() > 0) {
				dataApproval = listDataApproval.get(0);
				if (dataApproval.getCurrentApprovalLevel() > 0) {
					throw new RuntimeException(
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
			dataApproval.setEmpRequest(user.getEmployee());
			dataApproval.setCompany(user.getCompany());
			dataApproval.setCreatedDate(new Date());
			dataApproval.setModifiedDate(new Date());
			dataApproval.setObjectRef(request.getIdRef());
			dataApproval.setStatus(DataApproval.NOT_COMPLETED);
			dataApproval.setApprovalLevelOne(approvalLevelOne);
			dataApproval.setApprovalLevelTwo(approvalLevelTwo);
			dataApproval.setApprovalLevelThree(approvalLevelThree);
			currentAssignApproval = approvalLevelOne;
			dataApproval.setCurrentAssignApproval(currentAssignApproval);
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			dataApproval.setApprovalLevel(approvalLevel);
			dataApproval.setCreatedBy(user.getUsername());
			dataApproval.setModifiedBy(user.getUsername());
			dataApproval.setObjectName(request.getObjectName());
			dataApproval.setDescription(request.getDescription());
		}
		
		if(request.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			dataApproval.setDescription(workflow.getDescription());
			dataApproval.setObjectName(Employee.class.getSimpleName());
		}

		save(dataApproval);
		if (request.getAttachments() != null
				&& request.getAttachments().size() > 0) {

			// save attachment`
			for (Map<String, Object> map : request.getAttachments()) {
				String imageBase64 = (String) map.get("image");
				System.out.println("image : " + (String) map.get("image"));
				if (imageBase64 != null) {
					String pathname = "workflow/"
							+ RandomStringUtils.randomAlphanumeric(10) + "."
							+ Utils.UPLOAD_IMAGE_TYPE;
					Utils.createImage(imageBase64, pathname);
					AttachmentDataApproval obj = new AttachmentDataApproval();
					obj.setPath(pathname);
					obj.setDataApproval(dataApproval.getId());
					attachmentDataApprovalService.save(obj);
				}

			}

		}

		// action after data approval saved

		if (request.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.requestMaritalStatus(dataApproval);
		}
	}

	@Transactional
	public void approval(ApprovalWorkflowDTO approvalWorkflow, User user) {
		DataApproval dataApproval = dataApprovalRepository
				.findOne(approvalWorkflow.getId());
		if (dataApproval == null) {
			throw new RuntimeException("Data Approval is not found");
		}

		if (dataApproval.getStatus().equals(DataApproval.COMPLETED)) {
			throw new RuntimeException(
					"Error : This request is already Completed");
		}

		String strEmployee = "#" + user.getEmployee() + "#";

		// cek dulu user punya akses approval gak
		if (!dataApproval.getCurrentAssignApproval().contains(strEmployee)) {
			throw new RuntimeException(
					"Error : You don't have permission to approve this request");
		}

		if (approvalWorkflow.getStatus().equals(DataApproval.APPROVED)) {

			// jika berhasil naikan current Approval level data approval
			Integer currentApprovalLevel = dataApproval
					.getCurrentApprovalLevel() + 1;

			if (currentApprovalLevel > dataApproval.getApprovalLevel()) {
				throw new RuntimeException(
						"Error : Current approval step is over");
			}
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			if (dataApproval.getApprovalLevel() == currentApprovalLevel) {
				dataApproval.setStatus(DataApproval.COMPLETED);
				dataApproval
						.setProcessingStatus(DataApproval.PROC_STATUS_APPROVE);
				callingApprovedMethod(dataApproval);

			} else {
				Integer nextLevelApproval = dataApproval
						.getCurrentApprovalLevel() + 1;
				if (nextLevelApproval == 2
						&& dataApproval.getApprovalLevelTwo() != null
						&& !dataApproval.getApprovalLevelTwo().isEmpty()) {
					dataApproval.setCurrentAssignApproval(dataApproval
							.getApprovalLevelTwo());
				} else if (nextLevelApproval == 3
						&& !dataApproval.getApprovalLevelThree().isEmpty()
						&& dataApproval.getApprovalLevelThree() != null) {
					dataApproval.setCurrentAssignApproval(dataApproval
							.getApprovalLevelThree());
				}
				dataApproval
						.setProcessingStatus(DataApproval.PROC_STATUS_WAITING);
			}

			System.out.println("ID Data Approval " + dataApproval.getId());
			System.out.println(dataApproval.toString());

		} else if (approvalWorkflow.getStatus().equals(DataApproval.REJECTED)) {
			dataApproval.setProcessingStatus(DataApproval.PROC_STATUS_REJECT);
			dataApproval.setStatus(DataApproval.COMPLETED);
			dataApproval.setReasonReject(approvalWorkflow.getReasonReject());
			String fullName = user.getFirstName();
			if (user.getLastName() != null)
				fullName += " " + user.getLastName();
			dataApproval.setRejectedBy(fullName);
			callingRejectedMethod(dataApproval);
		} else {
			throw new RuntimeException(
					"Error : Your action approval is undefined");
		}

		dataApprovalRepository.save(dataApproval);
	}

	private void callingRejectedMethod(DataApproval dataApproval) {
		if (dataApproval.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.rejectedChangeMaritalStatus(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
			leaveRequestService.rejected(dataApproval, LeaveRequest.REJECTED);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)) {
			familyService.rejected(dataApproval);
		} else if(dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)) {
			addressService.rejected(dataApproval);
		}else if(dataApproval.getTask().equals(Workflow.CHANGE_FAMILY)) {
			familyService.rejectedChange(dataApproval);
		}else if(dataApproval.getTask().equals(Workflow.CHANGE_ADDRESS)) {
			addressService.rejectedChange(dataApproval);
		}
	}

	private void callingApprovedMethod(DataApproval dataApproval) {
		if (dataApproval.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.approvedChangeMaritalStatus(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
			leaveRequestService.approved(dataApproval, LeaveRequest.APPROVED);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_FAMILY)) {
			familyService.approvedSubmitFamily(dataApproval);
		} else if (dataApproval.getTask().equals(Workflow.SUBMIT_ADDRESS)) {
			addressService.approvedSubmitAddress(dataApproval);
		}else if (dataApproval.getTask().equals(Workflow.CHANGE_FAMILY)) {
			familyService.approvedChangeFamily(dataApproval);
		}else if (dataApproval.getTask().equals(Workflow.CHANGE_ADDRESS)) {
			addressService.approvedChangeAddress(dataApproval);
		}
	}
	
	

	public void cancelRequest(ApprovalWorkflowDTO approvalWorkflow, User user) {
		DataApproval dataApproval = dataApprovalRepository
				.findOne(approvalWorkflow.getId());
		if (dataApproval == null) {
			throw new RuntimeException("Data Approval is not found");
		}

		if (dataApproval.getStatus().equals(DataApproval.COMPLETED)) {
			throw new RuntimeException(
					"Error : This request is already Completed");
		}
		
		

		System.out.println(user.getEmployee());
		
		if(!dataApproval.getEmpRequest().equals(user.getEmployee()) ) {
			throw new RuntimeException(
					"Error : You dont have access this request");
		}
		
		dataApproval.setStatus(DataApproval.COMPLETED);
		dataApproval
				.setProcessingStatus(DataApproval.PROC_STATUS_CANCEL);
		
		dataApprovalRepository.save(dataApproval);
		callingRejectedMethod(dataApproval);
		
	}

}
