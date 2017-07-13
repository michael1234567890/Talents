package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.TMRequest;

@Repository
public interface TMRequestRepository extends PagingAndSortingRepository<TMRequest,Long>{
	
	@Query
	 List<TMRequest> findByTmRequestHeader(Long tmRequestHeader);
	
	@Query("SELECT u FROM TMRequest u WHERE u.company=:company AND u.employment=:employment ORDER BY createdDate DESC")
	List<TMRequest> findByCompanyAndEmployment(@Param("company") Long company, @Param("employment") Long employment);
	
	@Query("SELECT u FROM TMRequest u WHERE u.company=:company AND u.employment=:employment AND u.module=:module ORDER BY createdDate DESC")
	List<TMRequest> findByCompanyAndEmploymentAndModule(@Param("company") Long company, @Param("employment") Long employment,@Param("module") String module);
	
	@Modifying
	@Query("UPDATE TMRequest set status=:status,needSync=true where tmRequestHeader=:headerId")
	void approvedByHeaderId(@Param("status") String status, @Param("headerId") Long headerId);
	
	@Modifying
	@Query("UPDATE TMRequest set amount=:amount, status=:status,needSync=true where tmRequestHeader=:headerId")
	void approvedMedicalOverlimitByHeaderId(@Param("amount") Double amount, @Param("status") String status, @Param("headerId") Long headerId);
	
	@Query("select p from TMRequest p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND LOWER(p.type)=LOWER(:type) AND p.startDate=:startDate AND LOWER(p.status)!='reject'")
	List<TMRequest> findTMRequestByStartDate(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("categoryType") String categoryType, @Param("type") String type, @Param("startDate") Date startDate);

}
