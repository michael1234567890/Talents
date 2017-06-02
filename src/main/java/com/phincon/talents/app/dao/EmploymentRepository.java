package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Employment;

@Repository
public interface EmploymentRepository extends PagingAndSortingRepository<Employment,Long>{
	
	
	 @Query
	 List<Employment> findByEmployee(Long employee);
	 
	
	
}
