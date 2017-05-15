package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;

@Repository
public interface AttachmentDataApprovalRepository extends PagingAndSortingRepository<AttachmentDataApproval,Long>{
	
	@Query
	 List<AttachmentDataApproval> findByDataApproval(Long dataApproval);
	
	
}
