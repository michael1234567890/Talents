package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.AddressTemp;

@Repository
public interface AddressTempRepository extends PagingAndSortingRepository<AddressTemp,Long>{
	
	
	@Query
	Iterable<AddressTemp> findByEmployee(Long employeeId);
	
	 
}
