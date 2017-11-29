package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.TMBalanceRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
import com.phincon.talents.app.dto.BenefitDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.TMBalance;
import com.phincon.talents.app.model.hr.TMRequest;
import com.phincon.talents.app.utils.Utils;

@Service
public class TMRequestService {
	
	@Autowired
	TMRequestRepository tmRequestRepository;

	@Autowired
	TMBalanceRepository tmBalanceRepository;
	
	@Autowired
	WorkflowService workflowService;
	
	
	@Autowired
	DataApprovalService dataApprovalService;
	
	@Transactional
	public TMRequest findById(Long id) {
		return tmRequestRepository.findOne(id);
	}
	
	
	
	
	@Transactional
	public void save(TMRequest obj) {
		tmRequestRepository.save(obj);
	}
	
	@Transactional
	public void ApproveWithHeaderId(Long headerId,List<TMRequest> listTmRequest) {
		tmRequestRepository.approvedByHeaderId(TMRequest.APPROVED, headerId);
		for (TMRequest tmRequest : listTmRequest) {	
			//List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), employmentId, module, category, type, tmRequest.getRequestDate());
			// List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(), tmRequest.getCategoryType().toLowerCase(), tmRequest.getType().toLowerCase()); 
			List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(),  tmRequest.getType().toLowerCase()); 
			
			TMBalance balance = null;
			if(listTmBalances != null && listTmBalances.size() > 0) {
				// TMBalance balance = listTmBalances.get(0);
				for (TMBalance tmBalanceLoop : listTmBalances) {
					if(tmBalanceLoop.getStartDate()!= null && tmBalanceLoop.getEndDate() != null){
						if(Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getStartDate(), ">=") && Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getEndDate(), "<=")) {
							// no limit is no need to add Map Balance
							balance = tmBalanceLoop;
						}
					}else {
						balance = tmBalanceLoop;
					}
					
				}
				tmBalanceRepository.approved(balance.getId());
			}
		}
	}
	
	@Transactional
	public void approveMedicalOverlimit(Long headerId,List<TMRequest> listTmRequest,Double amount) {
		
		tmRequestRepository.approvedMedicalOverlimitByHeaderId(amount,TMRequest.APPROVED, headerId);
		
		// update Balance Info
		for (TMRequest tmRequest : listTmRequest) {
			List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(), "Medical"); 
			TMBalance balance = null;
			if(listTmBalances != null && listTmBalances.size() > 0) {
				// TMBalance balance = listTmBalances.get(0);
				for (TMBalance tmBalanceLoop : listTmBalances) {
					if(tmBalanceLoop.getStartDate()!= null && tmBalanceLoop.getEndDate() != null){
						if(Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getStartDate(), ">=") && Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getEndDate(), "<=")) {
							// no limit is no need to add Map Balance
							balance = tmBalanceLoop;
						}
					}else {
						balance = tmBalanceLoop;
					}
					
				}
				
				Double adjustment = Double.valueOf("0");
				if(balance.getAdjustmentMedical() != null) {
					adjustment = balance.getAdjustmentMedical();
				}
				
				adjustment +=amount;
				
				balance.setAdjustmentMedical(adjustment);
				
				Double balanceEnd = Double.valueOf("0");
				if(balance.getBalanceEnd() != null)
					balanceEnd = balance.getBalanceEnd();
				
				balanceEnd += amount;
				balance.setBalanceEnd(balanceEnd);
				balance.setNeedSync(true);
				tmBalanceRepository.save(balance);
				
			}
		}
	}
	
	
	@Transactional
	public void rejectCancelHeaderId(Long headerId, String status) {
		// get All detail with Header
		List<TMRequest> listTmRequest = tmRequestRepository.findByTmRequestHeader(headerId);
		if(listTmRequest!=null && listTmRequest.size() > 0) {
			for (TMRequest tmRequest : listTmRequest) {
				//tmBalance = listTmBalance.get(0);
				//tmRequest.setStatus(TMRequest.REJECT);
				tmRequest.setStatus(status);
				tmRequest.setNeedSync(true);
				save(tmRequest);
				List<TMBalance> listTmBalance = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(),  tmRequest.getType().toLowerCase()); 
				TMBalance tmBalance = null;
				if(listTmBalance != null && listTmBalance.size() > 0){
					for (TMBalance tmBalanceLoop : listTmBalance) {
						if(tmBalanceLoop.getStartDate()!= null && tmBalanceLoop.getEndDate() != null){
							if(Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getStartDate(), ">=") && Utils.comparingDate(tmRequest.getRequestDate(), tmBalanceLoop.getEndDate(), "<=")) {
								// no limit is no need to add Map Balance
								tmBalance = tmBalanceLoop;
							}
						}else {
							tmBalance = tmBalanceLoop;
						}
						
					}
					Double balanceEnd;
					Double balanceUsed;
					if(tmBalance.getModule().toLowerCase().equals("time management")) {
						balanceEnd = tmBalance.getBalanceEnd() + tmRequest.getTotalWorkDay();
						 balanceUsed = tmBalance.getBalanceUsed() - tmRequest.getTotalWorkDay();
					}else {
						balanceEnd = tmBalance.getBalanceEnd() + tmRequest.getAmount();
						balanceUsed = tmBalance.getBalanceUsed() - tmRequest.getAmount();
					}
					
					if(tmBalance.getBalanceType() != null && ( tmBalance.getBalanceType().toLowerCase().equals("daily") || tmBalance.getBalanceType().toLowerCase().equals("one time (transaction)") )) {
						tmBalance.setBalanceUsed(balanceUsed);						
					} else if(tmBalance.getBalanceType() != null && (tmBalance.getBalanceType().toLowerCase().equals("one time (2 years)") || tmBalance.getBalanceType().toLowerCase().equals("one time (1 years)") || tmBalance.getBalanceType().toLowerCase().equals("one time (5 years)")) ){
						tmBalance.setBalanceUsed(balanceUsed);
					}else {
						tmBalance.setBalanceEnd(balanceEnd);
						tmBalance.setBalanceUsed(balanceUsed);
						
					}
					tmBalance.setLastClaimDate(tmBalance.getLastClaimDateBefore());
					tmBalance.setLastClaimDateBefore(null);
					
					tmBalanceRepository.save(tmBalance);
				}
					
				
					
			}
		}
		
	}
	
	
	
}
	

	

