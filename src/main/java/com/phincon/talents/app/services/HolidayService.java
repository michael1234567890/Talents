package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.HolidayRepository;
import com.phincon.talents.app.model.Holiday;

@Service
public class HolidayService {
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Transactional
	public Iterable<Holiday> findAll(){
		return holidayRepository.findAll();
	}
	
	@Transactional
	public Iterable<Holiday> findByCompany(Long company){
		return holidayRepository.findByCompany(company);
	}
	
	@Transactional
	public void save(Holiday obj){
		holidayRepository.save(obj);
	}
	
	@Transactional
	public Holiday findById(Long id){
		return holidayRepository.findOne(id);
	}
}
