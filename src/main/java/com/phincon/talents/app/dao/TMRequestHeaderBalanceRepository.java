package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.hr.TMRequestHeaderBalance;

public interface TMRequestHeaderBalanceRepository extends PagingAndSortingRepository<TMRequestHeaderBalance, Long> {
	
	@Query
	List<TMRequestHeaderBalance> findByTmRequestHeader(Long tmRequestHeader);
	

}
