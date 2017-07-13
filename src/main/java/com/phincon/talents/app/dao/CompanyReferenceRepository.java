package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.CompanyReference;

@Repository
public interface CompanyReferenceRepository extends PagingAndSortingRepository<CompanyReference,Long>{
	
	 @Query
	 List<CompanyReference> findByCompany(Long company);
	 
	 @Query
	 List<CompanyReference> findByCompanyAndCategory(Long company, String category);
	 

	 @Query
	 List<CompanyReference> findByCompanyAndCategoryAndField(Long company, String category, String field);
	 
	
	
}
