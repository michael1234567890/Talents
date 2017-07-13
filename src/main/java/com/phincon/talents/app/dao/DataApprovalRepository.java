package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.DataApproval;

@Repository
public interface DataApprovalRepository extends PagingAndSortingRepository<DataApproval,Long>{
	
	 @Query
	 List<DataApproval> findByEmpRequestAndTaskAndActiveAndStatus(Long employee,String task, boolean active, String status);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company")
	 List<DataApproval> findNeedApproval(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company);
	 
	 @Query("SELECT COUNT(u.id) FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company")
	 List<Long> countNeedApproval(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company);
	 
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company AND u.module=:module")
	 List<DataApproval> findNeedApprovalAndModule(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company, @Param("module") String module);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.empRequest=:employee ORDER BY modifiedDate DESC")
	 List<DataApproval> findByEmpRequest(@Param("employee") Long employee);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.empRequest=:employee AND u.module=:module ORDER BY modifiedDate DESC")
	 List<DataApproval> findByEmpRequestAndModule(@Param("employee") Long employee, @Param("module") String module);
	 	
}
