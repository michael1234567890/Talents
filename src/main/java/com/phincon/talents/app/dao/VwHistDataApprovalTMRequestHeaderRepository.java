package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.VwHistDataApprovalTMRequestHeader;

@Repository
public interface VwHistDataApprovalTMRequestHeaderRepository extends PagingAndSortingRepository<VwHistDataApprovalTMRequestHeader,Long>{
	
	 @Query
	 List<VwHistDataApprovalTMRequestHeader> findByRequesterEmployee(Long requesterEmployee);
	 
	 @Query
	 List<VwHistDataApprovalTMRequestHeader> findByEmployeeApproval(Long employeeApproval);
	 
	 @Query("select p from VwHistDataApprovalTMRequestHeader p where  p.employeeApproval=:employeeApproval AND p.company=:company AND p.createdDate >= :fromDate AND p.createdDate <= :toDate AND currentApproval != 0 ")
	 List<VwHistDataApprovalTMRequestHeader> findByEmployeeApprovalAndCompanyAndRangeDate(@Param("employeeApproval") Long employeeApproval,@Param("company") Long company,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	 
	 
}
