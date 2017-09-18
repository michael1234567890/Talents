package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.phincon.talents.app.model.RunningNumber;
@Repository
public interface RunningNumberRepository extends
		PagingAndSortingRepository<RunningNumber, Long> {

	@Query("select p from RunningNumber p where p.company=:company AND p.month=:month AND LOWER(p.prefix)=LOWER(:prefix)")
	List<RunningNumber> findByCompanyAndMonthAndPrefix(@Param("company") Long company, @Param("month") String month,
			@Param("prefix") String prefix);

	
}
