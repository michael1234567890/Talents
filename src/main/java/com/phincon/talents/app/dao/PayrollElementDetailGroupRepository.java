package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementDetailGroup;

@Repository
public interface PayrollElementDetailGroupRepository extends PagingAndSortingRepository<PayrollElementDetailGroup,Long>{
	
	
	 @Query("SELECT u FROM PayrollElementDetailGroup u WHERE u.payrollElementHeader=:headerId AND LOWER(u.elementType) = LOWER(:elementType)")
	 List<PayrollElementDetailGroup> findByPayrollElementHeaderAndElementType(@Param("headerId") Long headerId, @Param("elementType") String elementType);
	 
	
	
}
