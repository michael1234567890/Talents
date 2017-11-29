package com.phincon.talents.app.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.TimesheetRepository;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.dto.TimesheetDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Timesheet;
import com.phincon.talents.app.utils.Utils;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class TimesheetService {

	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	WorkflowService workflowService;
	
	@Autowired
	DataApprovalService dataApprovalService;	
	private ObjectMapper objectMapper = new ObjectMapper();



	@Transactional
	public void save(TimesheetDTO timesheetDTO, User user,String task) {
		Timesheet timesheet = null;
		if(timesheetDTO.getId() != null) {
			timesheet = timesheetRepository.findOne(timesheetDTO.getId());
			if(timesheet == null) {
				throw new CustomException("Your Timesheet ID is not found.");
			}
			
		}else {
			timesheet = new Timesheet();
			timesheet.setCreatedDate(new Date());
			timesheet.setCreatedBy(user.getUsername());
		}
		timesheet.setEmployee(user.getEmployee());
		timesheet.setModifiedBy(user.getUsername());
		timesheet.setModifiedDate(new Date());
		timesheet.setCompany(user.getCompany());
		mappingDtoToBean(timesheetDTO,timesheet);
		timesheet.setNeedSync(true);
		validationBeforeSave(timesheet);
		Workflow workflow = null;
		if(timesheet.getId() == null){
			 workflow = workflowService.findByCodeAndCompanyAndActive(
					task, user.getCompany(), true);
			
			if(workflow != null){
				timesheet.setStatus(Timesheet.STATUS_PENDING);
				timesheet.setNeedSync(false);
			}
		}
		
		timesheetRepository.save(timesheet);
		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(Timesheet.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(timesheet.getId());
			dataApprovalDTO.setTask(task);
			dataApprovalDTO.setModule(workflow.getModule());
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}
	}

	private void validationBeforeSave(Timesheet timesheet) {
		// is TimeSheet is already exist for today
		Date today = new Date();
		//List<Timesheet> listTimesheet = timesheetRepository.findByEmployee(employee, pageable)
	}

	private void mappingDtoToBean(TimesheetDTO request, Timesheet timesheet) {
		timesheet.setActivity(request.getActivity());
		timesheet.setStatus(request.getStatus());
		timesheet.setToday(request.getToday());
		timesheet.setStartTime(Utils.convertStringToDateTime(request.getStartTime()));
		timesheet.setEndTime(Utils.convertStringToDateTime(request.getEndTime()));
		
	}
	
}
