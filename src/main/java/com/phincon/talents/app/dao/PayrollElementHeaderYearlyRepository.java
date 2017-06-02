package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementHeaderYearly;

@Repository
public interface PayrollElementHeaderYearlyRepository extends PagingAndSortingRepository<PayrollElementHeaderYearly,Long>{
	
	@Query
	PayrollElementHeaderYearly findByExtId(String extId);
	
	@Query
	List<PayrollElementHeaderYearly> findByEmploymentAndCurrentYear(Long employment, String currentYear);
	
	
	
}
