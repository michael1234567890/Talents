package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Family;

@Repository
public interface FamilyRepository extends PagingAndSortingRepository<Family,Long>{
	@Query("select p from Family p where UPPER(p.name) like UPPER(?1) or " +
            "UPPER(p.name) like UPPER(?1)")
    List search(String term);
	
	@Query
	Iterable<Family> findByEmployee(Long employeeId);
	
	
}
