package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.PayrollElementHeader;

@Repository
public interface PayrollElementHeaderRepository extends PagingAndSortingRepository<PayrollElementHeader,Long>{
	

	  public static final String LATEST_PERIOD_DATE = "SELECT u.periodDate FROM PayrollElementHeader u WHERE u.employment=:employment Order By periodDate desc";

	  @Query("SELECT u FROM PayrollElementHeader u WHERE SUBSTRING(u.periodDate,1,7)  like :period AND u.employment=:employment")
	  List<PayrollElementHeader> findByMonthPeriodAndEmployee(@Param("period") String period, @Param("employment") Long employment);
	  
	  @Query("SELECT u FROM PayrollElementHeader u WHERE u.employment=:employment Order By periodDate desc")
	  List<PayrollElementHeader> findByLatestMonthAndEmployment(@Param("employment") Long employment, Pageable pageable);

//	  @Query(value = LATEST_PERIOD_DATE, nativeQuery = true)
//	  List<Object[]> latestPeriodDate(@Param("employment") Long employment, Pageable pageable);
	  
	  @Query(value = LATEST_PERIOD_DATE)
	  List<Object[]> latestPeriodDate(@Param("employment") Long employment, Pageable pageable);
	 // List<PayrollElementHeader> findLatestPayslipEmployee(Long user);

	
}
