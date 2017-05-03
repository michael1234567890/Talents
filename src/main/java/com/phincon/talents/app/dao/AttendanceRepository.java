package com.phincon.talents.app.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Attendance;

@Repository
public interface AttendanceRepository extends PagingAndSortingRepository<Attendance,Long>{
	
	 @Query
	 Iterable<Attendance> findByEmployee(Long employee);
	 
	 
	 @Query
	 Attendance findByTodayAndEmployee(Date today,Long employee);
	 
	
	
}
