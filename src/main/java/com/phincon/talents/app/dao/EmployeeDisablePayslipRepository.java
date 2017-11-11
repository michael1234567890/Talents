package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.EmployeeDisablePayslip;

@Repository
public interface EmployeeDisablePayslipRepository extends PagingAndSortingRepository<EmployeeDisablePayslip,Long>{
	
	 @Query
	 List<EmployeeDisablePayslip> findByCompanyAndEmployeeNo(Long company,String employeeNo);
	 
}
