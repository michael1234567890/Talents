package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.Workflow;

@Repository
public interface WorkflowRepository extends PagingAndSortingRepository<Workflow,Long>{
	
	 @Query
	 Workflow findByCodeAndCompany(String code, Long company);

	 @Query
	Workflow findByCodeAndCompanyAndActive(String code, Long company,
			Boolean active);
	 
	
	
}
