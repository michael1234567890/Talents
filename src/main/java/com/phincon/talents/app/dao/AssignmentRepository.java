package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Assignment;

@Repository
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment,Long>{
	
	 @Query
	 List<Assignment> findByEmployee(Long employee);
	 
	 @Query
	 List<Assignment> findByReportTo(Long reportTo);
	 
	
	
}
