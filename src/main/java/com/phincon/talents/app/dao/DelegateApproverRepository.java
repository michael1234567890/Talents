package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.phincon.talents.app.model.DelegateApprover;
public interface DelegateApproverRepository extends PagingAndSortingRepository<DelegateApprover, Long> {
	
	 @Query("select p from DelegateApprover p where  p.employee=:employee AND p.company=:company AND p.fromDate <= :today AND p.toDate >= :today")
	 List<DelegateApprover> findByEmployeeAndCompany(@Param("employee") Long employee,@Param("company") Long company,@Param("today") Date today);
	 
}
