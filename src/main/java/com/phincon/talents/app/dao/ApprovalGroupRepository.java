package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.ApprovalGroup;

@Repository
public interface ApprovalGroupRepository extends PagingAndSortingRepository<ApprovalGroup,Long>{
	
	 @Query
	 List<ApprovalGroup> findByCodeAndCompanyAndActive(String code, Long company,Boolean active);
	 
	
	
}
