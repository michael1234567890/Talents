package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.FamilyTemp;

@Repository
public interface FamilyTempRepository extends PagingAndSortingRepository<FamilyTemp,Long>{
	@Query("select p from FamilyTemp p where UPPER(p.name) like UPPER(?1) or " +
            "UPPER(p.name) like UPPER(?1)")
    List search(String term);
	
	@Query
	Iterable<FamilyTemp> findByEmployee(Long employeeId);
	
	 @Modifying
	 @Query("UPDATE FamilyTemp set status='"+Family.APPROVED+"', needSync=true where id=:familyId")
	 void approvedSubmitFamily(@Param("familyId") Long familyId);
	 
}
