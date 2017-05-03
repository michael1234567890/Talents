package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Employee;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address,Long>{
	
	 @Query
	 Iterable<Address> findByEmployee(Employee employee);
	 
	
	
}
