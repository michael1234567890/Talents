package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.FamilyHist;

@Repository
public interface FamilyHistRepository extends PagingAndSortingRepository<FamilyHist,Long>{
	
	@Query
	List<FamilyHist> findByEmployee(Long employeeId);
	
	@Query
	List<FamilyHist> findByFamilyIdRef(Long familyIdRef);
	
	 
}
