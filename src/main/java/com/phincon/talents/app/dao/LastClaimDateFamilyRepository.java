package com.phincon.talents.app.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.app.model.hr.LastClaimDateFamily;

public interface LastClaimDateFamilyRepository extends PagingAndSortingRepository<LastClaimDateFamily, Long> {
	
	@Query
	LastClaimDateFamily findByBalanceAndFamilyAndEmployment(Long balanceId, Long familyId, Long employment);
	
}
