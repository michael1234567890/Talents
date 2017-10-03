package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Timesheet;

@Repository
public interface TimesheetRepository extends PagingAndSortingRepository<Timesheet, Long> {

	 @Query("SELECT u FROM Timesheet u WHERE u.employee=:employee ORDER BY modifiedDate DESC")
	 List<Timesheet> findByEmployee(@Param("employee") Long employee,Pageable pageable);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.employee=:employee ORDER BY modifiedDate DESC")
	 List<Timesheet> findByEmployeeAndToday(@Param("employee") Long employee,Pageable pageable);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.id=:id AND u.employee=:employee ORDER BY modifiedDate DESC")
	 List<Timesheet> findByIdAndEmployee(@Param("id") Long id,@Param("employee") Long employee);
	 
	 
	 @Query("SELECT COUNT(u.id) FROM Timesheet u WHERE u.employee=:employee")
	 List<Long> countByEmployee(@Param("employee") Long employee);
	 
	 
	 
}
