package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.TMBalanceRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
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
			// get balance based on like module,  category type,type
			
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
			// get balance based on like module,  category type,type
			String module = tmRequest.getModule();
			Long employmentId = tmRequest.getEmployment();
			// List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), employmentId, module, "Medical", "Medical", tmRequest.getRequestDate());
			// List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(), "Medical", "Medical"); 
			List<TMBalance> listTmBalances = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(), "Medical"); 
			System.out.println("listTmBalances");
			System.out.println(listTmBalances.size());
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
	public void rejectWithHeaderId(Long headerId) {
		// get All detail with Header
		List<TMRequest> listTmRequest = tmRequestRepository.findByTmRequestHeader(headerId);
		if(listTmRequest!=null && listTmRequest.size() > 0) {
			for (TMRequest tmRequest : listTmRequest) {
				//tmBalance = listTmBalance.get(0);
				tmRequest.setStatus(TMRequest.REJECT);
				tmRequest.setNeedSync(false);
				save(tmRequest);
				//List<TMBalance> listTmBalance = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(), tmRequest.getCategoryType().toLowerCase(), tmRequest.getType().toLowerCase()); 
				List<TMBalance> listTmBalance = tmBalanceRepository.findBalanceTypeByEmployment(tmRequest.getCompany(), tmRequest.getEmployment(), tmRequest.getModule().toLowerCase(),  tmRequest.getType().toLowerCase()); 
				System.out.println("Balance Size " + listTmBalance.size());
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
					
					Double balanceEnd = tmBalance.getBalanceEnd() + tmRequest.getAmount();
					Double balanceUsed = tmBalance.getBalanceUsed() - tmRequest.getAmount();
					if(tmBalance.getBalanceType() != null && ( tmBalance.getBalanceType().toLowerCase().equals("daily") || tmBalance.getBalanceType().toLowerCase().equals("one time (transaction)") )) {
						tmBalance.setBalanceUsed(balanceUsed);
						//tmBalance.setLastClaimDate(null);
						
					} else if(tmBalance.getBalanceType() != null && tmBalance.getBalanceType().toLowerCase().equals("one time")){
						tmBalance.setBalanceEnd(balanceEnd);
						tmBalance.setBalanceUsed(balanceUsed);
						
						
						// tmBalance.setLastClaimDate(null);
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
