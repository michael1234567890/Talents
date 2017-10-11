package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.RequestType;

@Repository
public interface RequestTypeRepository extends PagingAndSortingRepository<RequestType,Long>{
	
	@Query
	List<RequestType> findByCompany(Long company);
	
	@Query("SELECT u FROM RequestType u WHERE u.company=:company AND u.active=:active")
	List<RequestType> findByCompanyAndActive(@Param("company") Long company, @Param("active") boolean active);

	@Query("SELECT u FROM RequestType u WHERE u.company=:company AND LOWER(u.module)=LOWER(:module) AND u.active=:active")
	List<RequestType> findByCompanyAndModuleAndActive(@Param("company") Long company, @Param("module") String module,  @Param("active") boolean active);
	
	@Query("SELECT u FROM RequestType u WHERE u.company=:company AND LOWER(u.module)=LOWER(:module) AND u.categoryType=LOWER(:categoryType) AND u.type=LOWER(:type)")
	List<RequestType> findByCompanyAndModuleAndCategoryAndType(@Param("company") Long company, @Param("module") String module,  @Param("categoryType") String categoryType,  @Param("type") String type);
	
	
		
}
