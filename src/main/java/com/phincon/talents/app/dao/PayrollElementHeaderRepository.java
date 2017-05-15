package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementHeader;

@Repository
public interface PayrollElementHeaderRepository extends PagingAndSortingRepository<PayrollElementHeader,Long>{
	
	
	  @Query("SELECT u FROM PayrollElementHeader u WHERE SUBSTRING(u.periodDate,1,7)  like :period AND u.employee=:employee")
	  List<PayrollElementHeader> findByMonthPeriodAndEmployee(@Param("period") String period, @Param("employee") Long employee);

	
}
