package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.hr.LogUserActivity;

public interface LogUserActivityRepository extends PagingAndSortingRepository<LogUserActivity, Long>{
	
	@Query
	Iterable<LogUserActivity> getByUsername(String username);
}
