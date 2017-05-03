package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.LeaveType;

@Repository
public interface LeaveTypeRepository extends PagingAndSortingRepository<LeaveType,Long>{
	
	 @Query
	 List<LeaveType> findByCompany(Long company);
	 
	
	
}
