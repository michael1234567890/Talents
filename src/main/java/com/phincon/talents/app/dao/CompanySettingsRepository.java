package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.CompanySettings;

@Repository
public interface CompanySettingsRepository extends PagingAndSortingRepository<CompanySettings,Long>{
	
	 @Query
	 List<CompanySettings> findByCompany(Long company);
	 
	
	
}
