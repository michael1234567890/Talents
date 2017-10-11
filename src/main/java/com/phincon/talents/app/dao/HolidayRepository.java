package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.Holiday;

public interface HolidayRepository extends PagingAndSortingRepository<Holiday, Long>{

	@Query
	List<Holiday> findByCompany(Long company);
}
