package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.ApprovalGroupRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.WorkflowRepository;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.ApprovalGroup;
import com.phincon.talents.app.model.hr.Employee;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class WorkflowService {

	@Autowired
	WorkflowRepository dataApprovalRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ApprovalGroupRepository approvalGroupRepository;

	@Autowired
	VwEmpAssignmentService vwEmpAssignmentService;

	@Transactional
	public Workflow findById(Long id) {
		return dataApprovalRepository.findOne(id);
	}

	@Transactional
	public Iterable<Workflow> findAll() {
		return dataApprovalRepository.findAll();
	}

	@Transactional
	public Workflow findByCodeAndCompanyAndActive(String code, Long company,Boolean active) {
		return dataApprovalRepository.findByCodeAndCompanyAndActive(code, company,active);
	}

	@Transactional
	public void save(Workflow obj) {
		dataApprovalRepository.save(obj);
	}

	@Transactional
	public String findAssignApproval(String codeApproval, Long employee,
			Long company) {
		String assignApproval = null;
		if (codeApproval.equals(Workflow.DEFAULT)) {

			System.out.println(" findAssignApproval Employee ID " + employee);
			// get direct report
			Employee objEmployee = vwEmpAssignmentService
					.findReportTo(employee);
			if (objEmployee != null)
				assignApproval = "#" + objEmployee.getId() + "#";
		} else {
			// get group of employee for approve
			List<ApprovalGroup> listApprovalGroup = approvalGroupRepository
					.findByCodeAndCompanyAndActive(codeApproval, company, true);
			if (listApprovalGroup != null && listApprovalGroup.size() > 0) {
				ApprovalGroup approvalGroup = listApprovalGroup.get(0);
				assignApproval = approvalGroup.getMember();
				if(approvalGroup.getHaveType()!=null && approvalGroup.getHaveType()) {
					String memberType = null;
					Employee employeeObj = employeeRepository.findOne(employee);
					
					if(employeeObj != null && employeeObj.getBenefitType()!=null && !employeeObj.getBenefitType().equals("")){
						if(employeeObj.getBenefitType().equals(Employee.APPROVAL_1))
							memberType = approvalGroup.getMemberTypeOne();
						else if(employeeObj.getBenefitType().equals(Employee.APPROVAL_2))
							memberType = approvalGroup.getMemberTypeTwo();
						else if(employeeObj.getBenefitType().equals(Employee.APPROVAL_3))
							memberType = approvalGroup.getMemberTypeThree();
						else if(employeeObj.getBenefitType().equals(Employee.APPROVAL_4))
							memberType = approvalGroup.getMemberTypeFour();
					}
				
					if(memberType!= null && !memberType.equals(""))
						assignApproval = memberType;
				}
			}
		}
		return assignApproval;
	}

}
