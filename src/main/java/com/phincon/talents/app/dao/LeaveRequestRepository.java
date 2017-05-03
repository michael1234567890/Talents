package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends PagingAndSortingRepository<LeaveRequest,Long>{
	
	 @Query
	 LeaveRequest findByCompany(Long company);

	 @Query
	List<LeaveRequest> findByStatus(String status);
	 
	 @Query
		List<LeaveRequest> findByEmployee(Long employee);
	 
	 @Query
		List<LeaveRequest> findByEmployeeAndStatus(Long employee, String status);
	 
	 @Modifying
	 @Query("UPDATE LeaveRequest set status=:status where id=:id")
	 void updateStatus(@Param("status") String status, @Param("id") Long id);
	 
	
	
}
