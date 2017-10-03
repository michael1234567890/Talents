package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.Holiday;

public interface HolidayRepository extends PagingAndSortingRepository<Holiday, Long>{

	@Query
	Iterable<Holiday> findByCompany(Long company);
}
