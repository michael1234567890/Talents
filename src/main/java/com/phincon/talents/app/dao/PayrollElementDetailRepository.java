package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementDetail;

@Repository
public interface PayrollElementDetailRepository extends PagingAndSortingRepository<PayrollElementDetail,Long>{
	
	
	 @Query("SELECT u FROM PayrollElementDetail u WHERE u.payrollElementHeader=:headerId AND LOWER(u.elementType) = LOWER(:elementType)")
	 List<PayrollElementDetail> findByPayrollElementHeaderAndElementType(@Param("headerId") Long headerId, @Param("elementType") String elementType);
	 
	
	
}
