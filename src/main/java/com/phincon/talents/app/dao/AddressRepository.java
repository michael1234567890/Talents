package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Family;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address,Long>{
	
	 @Query
	 Iterable<Address> findByEmployee(Long employee);
	 
	 @Modifying
	 @Query("UPDATE Address set status='"+Address.STATUS_APPROVED+"', needSync=true where id=:addressId")
	 void approvedSubmitAddress(@Param("addressId") Long addressId);

	
	 
	@Modifying
	 @Query("UPDATE Address set status='"+Address.STATUS_APPROVED+"', needSync=true, addressTemp=null where id=:addressId")
	 void approvedChangeAddress(@Param("addressId") Long addressId);
	
}
