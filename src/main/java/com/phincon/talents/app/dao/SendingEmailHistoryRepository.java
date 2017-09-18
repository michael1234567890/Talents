package com.phincon.talents.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.SendingEmailHistory;

@Repository
public interface SendingEmailHistoryRepository extends PagingAndSortingRepository<SendingEmailHistory,Long>{
	
}
