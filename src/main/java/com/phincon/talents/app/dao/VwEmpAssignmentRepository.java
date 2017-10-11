package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.VwEmpAssignment;

@Repository
public interface VwEmpAssignmentRepository extends PagingAndSortingRepository<VwEmpAssignment,Long>{
	
	 @Query
	 List<VwEmpAssignment> findByEmployee(Long employee);
	 
	 @Query("select p from VwEmpAssignment p where p.employee=:employee AND p.gradeName != NULL")
	 List<VwEmpAssignment> findWithGradeByEmployee(@Param("employee") Long employee);
	 
	 @Query
	 List<VwEmpAssignment> findByDirectEmployee(Long reportTo);
	 
	 @Query
	 List<VwEmpAssignment> findByOrganizationAndCompany(Long organization,Long company,Pageable pageable);
	 
	 
	 @Query("select p from VwEmpAssignment p where LOWER(p.division)=LOWER(:division) AND p.company=:company")
	 List<VwEmpAssignment> findByDivisionAndCompany(@Param("division") String division,@Param("company") Long company,Pageable pageable);
	 
	 @Query("SELECT COUNT(u.id) FROM VwEmpAssignment u WHERE u.organization=:organization AND u.company=:company")
	 List<Long> countByOrganizationAndCompany(@Param("organization") Long organization,@Param("company") Long company);
	
	 @Query("SELECT COUNT(u.id) FROM VwEmpAssignment u WHERE LOWER(u.division)=LOWER(:division) AND u.company=:company")
	 List<Long> countByDivisionAndCompany(@Param("division") String division,@Param("company") Long company);
	
	
}
