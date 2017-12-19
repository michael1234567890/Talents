package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.DataApprovalHistory;

public interface DataApprovalHistoryRepository extends PagingAndSortingRepository<DataApprovalHistory, Long>{

	@Query
	List<DataApprovalHistory> findByCompany(Long company);
}
