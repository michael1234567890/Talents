package com.phincon.talents.app.services;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LogUserActivityRepository;

@Service
public class AsyncServices {
	
	@Autowired
	LogUserActivityRepository logUserRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Async
	public Future<String> process() throws InterruptedException{
		System.out.println("###Start Processing with Thread id: " + Thread.currentThread().getId());
		
		Thread.sleep(3000);
		String processInfo = String.format("Processing is Done with Thread id= %d", Thread.currentThread().getId());
		
		return new AsyncResult<String>(processInfo); 
	}
	
}
