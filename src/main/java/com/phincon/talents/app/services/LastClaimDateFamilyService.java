package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LastClaimDateFamilyRepository;
import com.phincon.talents.app.model.hr.LastClaimDateFamily;

@Service
public class LastClaimDateFamilyService {
	
	@Autowired
	LastClaimDateFamilyRepository lastClaimDateFamilyRepository;
	
	@Transactional
	public Iterable<LastClaimDateFamily> findAll(){
		return lastClaimDateFamilyRepository.findAll();
	}
	
	@Transactional
	public LastClaimDateFamily findByBalanceAndFamilyAndEmployment(Long balanceId, Long familyId, Long employmentId){
		return lastClaimDateFamilyRepository.findByBalanceAndFamilyAndEmployment(balanceId, familyId, employmentId);
	}
	
	@Transactional
	public void save(LastClaimDateFamily lastClaimDateFamily){
		lastClaimDateFamilyRepository.save(lastClaimDateFamily);
	}
}
