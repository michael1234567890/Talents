package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementDetail;

@Repository
public interface PayrollElementDetailRepository extends PagingAndSortingRepository<PayrollElementDetail,Long>{
	
	 @Query
	 List<PayrollElementDetail> findByPayrollElementHeaderAndElementType(Long headerId, String elementType);
	 
	
	
}
