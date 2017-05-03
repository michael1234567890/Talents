package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Entitlement;

@Repository
public interface EntitlementRepository extends PagingAndSortingRepository<Entitlement,Long>{
	
	 @Query
	 List<Entitlement> findByEmployeeAndLeaveType(Long employee,Long leaveType);
	 
	 @Query
	 List<Entitlement> findByEmployee(Long employee);
	 
	
	
}
