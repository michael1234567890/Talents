package com.phincon.talents.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.phincon.talents.app.model.hr.ApproverSpesific;
public interface ApproverSpesificRepository extends PagingAndSortingRepository<ApproverSpesific, Long> {
	
	 @Query
	 List<ApproverSpesific> findByCompanyAndTask(Long company, String task);
	 
	 @Query
	 List<ApproverSpesific> findByCompanyAndTaskAndWorkLocation(Long company, String task, String workLocation);

}
