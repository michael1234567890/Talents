package com.phincon.talents.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Leave;

@Repository
public interface LeaveRepository extends
		PagingAndSortingRepository<Leave, Long> {

}
