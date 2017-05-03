package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.LeaveRequest;

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
	LeaveRequestService leaveRequestService;
	
	
	@Autowired
	EmployeeService employeeService;


	@Transactional
	public DataApproval findById(Long id) {
		return dataApprovalRepository.findOne(id);
	}

	@Transactional
	public Iterable<DataApproval> findAll() {
		return dataApprovalRepository.findAll();
	}
	
	@Transactional
	public List<DataApproval> findNeedApproval(String emp, String status, Long company) {
		List<DataApproval> listDataApprovals = dataApprovalRepository.findNeedApproval(emp, status, company);
		List<DataApproval> lisApprovalsTemp = new ArrayList<DataApproval>();
		if(listDataApprovals != null && listDataApprovals.size() > 0) {
			for (DataApproval dataApproval : listDataApprovals) {
				Employee objEmployee = employeeService.findEmployee(dataApproval.getEmpRequest());
				dataApproval.setEmployeeRequest(objEmployee);
				if(dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
					LeaveRequest obj = leaveRequestService.findById(dataApproval.getObjectRef());
					Object objConvert = (Object) obj;
					dataApproval.setRef(objConvert);
				}
				lisApprovalsTemp.add(dataApproval);
			}
		}
		return lisApprovalsTemp;
	}

	@Transactional
	public List<DataApproval> findByEmployeeAndTaskAndActive(Long employee,
			String task, boolean active) {
		return dataApprovalRepository.findByEmpRequestAndTaskAndActive(
				employee, task, active);
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
		
		approvalLevelOne  = workflowService.findAssignApproval(
				workflow.getApprovalCodeLevelOne(), user.getEmployee(),
				user.getCompany());
		
		if (workflow.getApprovalCodeLevelTwo() != null
				&& (!workflow.getApprovalCodeLevelTwo().equals("")|| !workflow.getApprovalCodeLevelTwo().isEmpty())){
			approvalLevel = 2;
			System.out.println("Code Approval Lev 2 " + workflow.getApprovalCodeLevelTwo());
			approvalLevelTwo = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelTwo(), user.getEmployee(),
					user.getCompany());
		}
			

		if (workflow.getApprovalCodeLevelThree() != null
				&& !workflow.getApprovalCodeLevelThree().equals("")){
			approvalLevel = 3;
			approvalLevelThree = workflowService.findAssignApproval(
					workflow.getApprovalCodeLevelTwo(), user.getEmployee(),
					user.getCompany());
		}
			

		if (workflow.getOperation() != null
				&& workflow.getOperation().equals(Workflow.OPERATION_EDIT)) {
			List<DataApproval> listDataApproval = findByEmployeeAndTaskAndActive(
					user.getEmployee(), request.getTask(), true);
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
			currentAssignApproval = approvalLevelOne ;
			dataApproval.setCurrentAssignApproval(currentAssignApproval);
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			dataApproval.setApprovalLevel(approvalLevel);
			dataApproval.setCreatedBy(user.getUsername());
			dataApproval.setModifiedBy(user.getUsername());
			
		}
		save(dataApproval);
	}

	//@Transactional
	public void approval(ApprovalWorkflowDTO approvalWorkflow, User user) {

		if (approvalWorkflow.getStatus().equals(DataApproval.APPROVED)) {
			DataApproval dataApproval = dataApprovalRepository
					.findOne(approvalWorkflow.getId());
			if(dataApproval.getStatus().equals(DataApproval.COMPLETED)) {
				throw new RuntimeException(
						"Error : This request is already Completed");
			}
			String strEmployee = "#" + user.getEmployee() + "#";
			
			// cek dulu user punya akses approval gak
			if (!dataApproval.getCurrentAssignApproval().contains(strEmployee)) {
				throw new RuntimeException(
						"Error : You don't have permission to approve this request");
			}

			// cek apa operation nya edit or insert

			// calling masing2 function sesuai dengan task

			// jika berhasil naikan current Approval level data approval
			Integer currentApprovalLevel = dataApproval
					.getCurrentApprovalLevel() + 1;
			if(currentApprovalLevel > dataApproval.getApprovalLevel()) {
				throw new RuntimeException(
						"Error : Current approval step is over");
			}
			dataApproval.setCurrentApprovalLevel(currentApprovalLevel);
			if (dataApproval.getApprovalLevel() == currentApprovalLevel) {
				dataApproval.setStatus(DataApproval.COMPLETED);
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
			}

			System.out.println("ID Data Approval " + dataApproval.getId());
			System.out.println(dataApproval.toString());
			
			dataApprovalRepository.save(dataApproval);
		} else if (approvalWorkflow.getStatus().equals(DataApproval.REJECTED)) {

		} else {
			throw new RuntimeException(
					"Error : Your action approval is undefined");
		}
	}
	
   

	
	private void callingApprovedMethod(DataApproval dataApproval){
		if(dataApproval.getTask().equals(Workflow.CHANGE_MARITAL_STATUS)) {
			employeeService.approvedChangeMaritalStatus(dataApproval);
		}else if(dataApproval.getTask().equals(Workflow.SUBMIT_LEAVE)) {
			leaveRequestService.approved(dataApproval,LeaveRequest.APPROVED);
		}
	}

}
