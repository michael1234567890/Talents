package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.Family;

@Repository
public interface DataApprovalRepository extends PagingAndSortingRepository<DataApproval,Long>{
	
	
	@Query
	DataApproval findByIdAndEmpRequest(Long id ,Long employee);
	
	 @Query
	 List<DataApproval> findByEmpRequestAndTaskAndActiveAndStatus(Long employee,String task, boolean active, String status);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company")
	 List<DataApproval> findNeedApproval(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.company=:company")
	 List<DataApproval> findAll(@Param("emp") String emp, @Param("company") Long company);
	 
	 @Query("SELECT COUNT(u.id) FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company")
	 List<Long> countNeedApproval(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company);
	 
	 @Query("SELECT COUNT(u.id) FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.company=:company")
	 List<Long> countAll(@Param("emp") String emp, @Param("company") Long company);
	 
	 @Query("SELECT COUNT(u.id) FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company AND u.module=:module")
	 List<Long> countNeedApprovalAndModule(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company, @Param("module") String module);
	 
	 @Query("SELECT COUNT(u.id) FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.company=:company AND u.module=:module")
	 List<Long> countAllModule(@Param("emp") String emp, @Param("company") Long company, @Param("module") String module);
	 
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.status=:status AND u.company=:company AND u.module=:module")
	 List<DataApproval> findNeedApprovalAndModule(@Param("emp") String emp, @Param("status") String status, @Param("company") Long company, @Param("module") String module,Pageable pageable);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.currentAssignApproval like %:emp% AND u.company=:company AND u.module=:module")
	 List<DataApproval> findAllModule(@Param("emp") String emp, @Param("company") Long company, @Param("module") String module,Pageable pageable);
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.empRequest=:employee ORDER BY modifiedDate DESC")
	 List<DataApproval> findByEmpRequest(@Param("employee") Long employee,Pageable pageable);
	 
	 @Query("SELECT count(u.id) FROM DataApproval u WHERE u.empRequest=:employee ORDER BY modifiedDate DESC")
	 List<Long> countByEmpRequest(@Param("employee") Long employee);
	 
	 
	 @Query("SELECT u FROM DataApproval u WHERE u.empRequest=:employee AND u.module=:module ORDER BY modifiedDate DESC")
	 List<DataApproval> findByEmpRequestAndModule(@Param("employee") Long employee, @Param("module") String module,Pageable pageable);
	 
	 @Query("SELECT count(u.id) FROM DataApproval u WHERE u.empRequest=:employee AND u.module=:module ORDER BY modifiedDate DESC")
	 List<Long> countByEmpRequestAndModule(@Param("employee") Long employee, @Param("module") String module);
	 
	 
	 
	 @Query
	 List<DataApproval> findByCompanyAndObjectNameAndObjectRef(Long compay,String objectName, Long objectRef);
	 	
}
