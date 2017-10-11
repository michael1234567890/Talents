package com.phincon.talents.app.dao;

import java.util.Date;
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
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.status=:status ORDER BY modifiedDate DESC")
	 List<Timesheet> findByStatus(@Param("status") String status, Pageable pageable);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.employee=:employee ORDER BY modifiedDate DESC")
	 List<Timesheet> findByEmployeeAndToday(@Param("employee") Long employee,Pageable pageable);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.id=:id AND u.employee=:employee ORDER BY modifiedDate DESC")
	 List<Timesheet> findByIdAndEmployee(@Param("id") Long id,@Param("employee") Long employee);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.employee = :employee AND u.company = :company AND u.today >= :startDate AND u.today <= :endDate ORDER BY modifiedDate DESC")
	 List<Timesheet> findByEmployeeAndCompanyAndToday(@Param("employee") Long employee, @Param("company") Long company, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	 
	 @Query("SELECT u FROM Timesheet u WHERE u.employee = :employee AND u.company = :company AND SUBSTRING(u.today,1,7) like %:period%")
	 List<Timesheet> findByEmployeeAndCompanyAndMonthPeriod(@Param("employee") Long employee, @Param("company") Long company, @Param("period") String period);
	 
	 @Query("SELECT COUNT(u.id) FROM Timesheet u WHERE u.employee=:employee")
	 List<Long> countByEmployee(@Param("employee") Long employee);
	 
	 @Query("SELECT COUNT(u.id) FROM Timesheet u WHERE u.status = :status")
	 List<Long> countByStatus(@Param("status") String status);
	 
}
