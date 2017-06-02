package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementDetailGroup;
import com.phincon.talents.app.model.hr.PayrollElementDetailYearly;

@Repository
public interface PayrollElementDetailYearlyRepository extends PagingAndSortingRepository<PayrollElementDetailYearly,Long>{
	
	
	
	@Query
	PayrollElementDetailYearly findByExtId(String extId);
	
	@Query
	List<PayrollElementDetailYearly> findByPayrollElementHeaderYearlyExtIdAndElementGroupAndElementType(String headerExtId,String elementGroup, String elementType);
	

	 @Query("SELECT u FROM PayrollElementDetailYearly u WHERE u.payrollElementHeaderYearly=:headerId AND LOWER(u.elementType) = LOWER(:elementType)")
	 List<PayrollElementDetailYearly> findByPayrollElementHeaderAndElementType(@Param("headerId") Long headerId, @Param("elementType") String elementType);
}
