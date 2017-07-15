package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.RequestCategoryType;

@Repository
public interface RequestCategoryTypeRepository extends PagingAndSortingRepository<RequestCategoryType,Long>{
	
	@Query
	List<RequestCategoryType> findByCompany(Long company);
	
}
