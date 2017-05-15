package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Organization;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>{
	@Query
	Organization findByExtId(String extId);
	
	@Query("SELECT u FROM Organization u WHERE u.extId is NULL")
	List<Organization> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Organization SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Organization SET extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);
}
