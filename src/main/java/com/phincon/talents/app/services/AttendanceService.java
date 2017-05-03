package com.phincon.talents.app.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.AttendanceRepository;
import com.phincon.talents.app.model.hr.Attendance;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Transactional
	public Iterable<Attendance> findByEmployee(Long employee) {
		Iterable<Attendance> list = attendanceRepository
				.findByEmployee(employee);
		return list;
	}
	
	@Transactional
	public void save(Attendance attendance) {
		attendanceRepository.save(attendance);
	}
	
	@Transactional
	public Attendance findByTodayAndEmployee(Date today, Long employee) {
		System.out.println("Today " + today);
		System.out.println("Employee " + employee);
		
		Attendance obj = attendanceRepository.findByTodayAndEmployee(today, employee);
		return obj;
	}
	
	
	

	

}
