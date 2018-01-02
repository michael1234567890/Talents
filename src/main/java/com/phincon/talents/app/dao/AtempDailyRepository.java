package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.AtempDaily;

@Repository
public interface AtempDailyRepository extends PagingAndSortingRepository<AtempDaily, Long> {
	
	@Query
	Iterable<AtempDaily> findByEmployeeNo(String employeeNo);
	
	@Query
	AtempDaily findByExtId(String extId);
	
	@Query("SELECT u FROM AtempDaily u WHERE u.employeeNo = :employeeNo ORDER BY modifiedDate DESC")
	List<AtempDaily> findByEmployeeNo(@Param("employeeNo") String employeeNo, Pageable pageable);
	
	@Query("SELECT u FROM AtempDaily u WHERE u.employeeNo = :employeeNo AND u.company = :company AND SUBSTRING(u.workDate,1,7) like %:period%")
	List<AtempDaily> findByEmployeeAndCompanyAndMonthPeriod(@Param ("employeeNo") String employeeNo, @Param("company") Long company, @Param("period") String period);
	
	@Query("SELECT u FROM AtempDaily u WHERE u.employeeNo = :employeeNo AND u.company = :company AND u.workDate >= :startDate AND u.workDate <= :endDate ORDER BY modifiedDate DESC")
	List<AtempDaily> findByEmployeeNoAndCompanyAndWorkdate(@Param("employeeNo") String employeeNo, @Param("company") Long company, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT COUNT (u.id) FROM AtempDaily u WHERE u.employeeNo=:employeeNo")
	List<Long> countByEmployeeNo(@Param("employeeNo") String employeeNo);
	
}
