package com.phincon.talents.app.dao;

import java.util.List;

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
	 
	
	
}
