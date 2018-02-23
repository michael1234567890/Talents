package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Family;

@Repository
public interface FamilyRepository extends PagingAndSortingRepository<Family,Long>{
	@Query("select p from Family p where UPPER(p.name) like UPPER(?1) or " +
            "UPPER(p.name) like UPPER(?1)")
    List search(String term);
	
	@Query
	Family findByIdAndEmployee(Long id ,Long employee);
	
	@Query
	Iterable<Family> findByEmployee(Long employeeId);

	@Query
	Iterable<Family> findByEmployeeAndIsEligibleKacamata(Long employeeId, Boolean isEligibleKacamata);
	
	@Query
	Iterable<Family> findByEmployeeAndIsEligibleMedical(Long employeeId, Boolean isEligibleMedical);
	
	@Query(nativeQuery= true, value = "SELECT * FROM hr_family u WHERE u.employee_id=:employeeId and relationship in ('Suami', 'Istri', 'Anak') order by u.birth_date limit 4")
	List<Family> getFamilyEligible(@Param("employeeId") Long employeeId);
	
	 @Modifying
	 @Query("UPDATE Family set status='"+Family.APPROVED+"', needSync=true where id=:familyId")
	 void approvedSubmitFamily(@Param("familyId") Long familyId);
	 
	 @Modifying
	 @Query("UPDATE Family set status='"+Family.APPROVED+"', needSync=true, familyTemp=null where id=:familyId")
	 void approvedChangeFamily(@Param("familyId") Long familyId);
	 
}
