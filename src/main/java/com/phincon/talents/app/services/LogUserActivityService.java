package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LogUserActivityRepository;
import com.phincon.talents.app.model.hr.LogUserActivity;

@Service
public class LogUserActivityService {
	
	@Autowired
	LogUserActivityRepository logUserActivityRepository;
	
	@Transactional
	public Iterable<LogUserActivity> findAll(){
		return logUserActivityRepository.findAll();
	}
	
	@Transactional
	public void save(LogUserActivity logUserActivity){
		logUserActivityRepository.save(logUserActivity);
	}
	
	@Transactional
	public Iterable<LogUserActivity> getByUsername(String username){
		return logUserActivityRepository.getByUsername(username);
	}
	
	
	
}
