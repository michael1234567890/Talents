package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.EmployeePayroll;

@Repository
public interface EmployeePayrollRepository extends PagingAndSortingRepository<EmployeePayroll, Long>{

	@Query
	EmployeePayroll findByExtId(String extId);
	
	@Query("SELECT u FROM EmployeePayroll u WHERE u.needSync=true")
	List<EmployeePayroll> findNeedSync();
	
	@Query
	EmployeePayroll findByEmployeeNo(String employmentExtId);
}
