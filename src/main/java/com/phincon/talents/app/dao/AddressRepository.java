package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Address;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address,Long>{
	
	@Query
	Address findByIdAndEmployee(Long id ,Long employee);
	 
	
	@Query
	 Iterable<Address> findByEmployee(Long employee);
	 
	 @Query
	 List<Address> findByAddressStatusAndEmployee(String addressStatus, Long employee);
	 
	 
	 
	 
	 @Modifying
	 @Query("UPDATE Address set status='"+Address.STATUS_APPROVED+"', needSync=true where id=:addressId")
	 void approvedSubmitAddress(@Param("addressId") Long addressId);

	
	 
	@Modifying
	 @Query("UPDATE Address set status='"+Address.STATUS_APPROVED+"', needSync=true, addressTemp=null where id=:addressId")
	 void approvedChangeAddress(@Param("addressId") Long addressId);
	
}
