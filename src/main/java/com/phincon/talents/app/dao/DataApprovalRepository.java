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
	 
	 @Query
	 List<DataApproval> findByEmpRequest(Long employee);
	 

	
	
}
