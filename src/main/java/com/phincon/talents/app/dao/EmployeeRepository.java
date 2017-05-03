package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{
	@Query("select p from Employee p where UPPER(p.firstName) like UPPER(?1) or " +
            "UPPER(p.lastName) like UPPER(?1)")
    List search(String term);
	
	
	 @Query
	 Employee findByOfficeMail(String email);
	 
	 @Query
	 List<Employee> findByIdIn(List<Long> ids);
	 
	 @Modifying
	 @Query("UPDATE Employee set maritalStatus=:maritalStatus where id=:employeeId")
	 void updateMaritalStatus(@Param("employeeId") Long employeeId, @Param("maritalStatus") String maritalStatus);
	 
	 
	
}
