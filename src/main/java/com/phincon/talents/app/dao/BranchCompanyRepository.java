package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.BranchCompany;

public interface BranchCompanyRepository extends PagingAndSortingRepository<BranchCompany, Long> {
	
	@Query
	List<BranchCompany> findByCompany(Long company);
	
}
