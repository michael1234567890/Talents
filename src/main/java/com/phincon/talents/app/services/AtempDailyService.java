package com.phincon.talents.app.services;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.AtempDailyRepository;
import com.phincon.talents.app.model.hr.AtempDaily;


@Service
public class AtempDailyService {

	@Autowired
	AtempDailyRepository atempDailyRepository;
	
	@Transactional
	public AtempDaily findAtempDaily(Long id){
		return atempDailyRepository.findOne(id);
	}
	
	@Transactional
	public Iterable<AtempDaily> findByEmployee(String employeeNo){
		return atempDailyRepository.findByEmployeeNo(employeeNo);
	}
	
	@Transactional
	public AtempDaily findByExtId(String extId){
		return atempDailyRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<AtempDaily> findAll(){
		return atempDailyRepository.findAll();
	}
	
	@Transactional
	public void save(AtempDaily obj){
		atempDailyRepository.save(obj);
	}
}
