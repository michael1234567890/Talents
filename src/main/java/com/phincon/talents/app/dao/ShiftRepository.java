package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Shift;

@Repository
public interface ShiftRepository extends PagingAndSortingRepository<Shift, Long>{
	
	@Query
	Shift findByExtId(String extId);
	
	@Query("SELECT u FROM Shift u WHERE u.needSync=true")
	List<Shift> findNeedSync();
	
	@Query
	List<Shift> findByCompany(Long company);
}
