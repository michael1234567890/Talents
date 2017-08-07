package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.TMBalance;

@Repository
public interface TMBalanceRepository extends PagingAndSortingRepository<TMBalance,Long>{
	
	
@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND p.startDate <=:today AND p.endDate>=:today")
List<TMBalance> findBalanceCategoryTypeByEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("categoryType") String categoryType, @Param("today")  Date today);

@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType)")
List<TMBalance> findBalanceCategoryTypeByEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("categoryType") String categoryType);

@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module)")
List<TMBalance> findBalanceByModuleEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module);



@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND LOWER(p.type)=LOWER(:type) AND p.startDate <=:today AND p.endDate>=:today")
List<TMBalance> findBalanceTypeByEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("categoryType") String categoryType, @Param("type") String type,   @Param("today")  Date today);

@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND LOWER(p.type)=LOWER(:type)")
List<TMBalance> findBalanceTypeByEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("categoryType") String categoryType, @Param("type") String type);

@Query("select p from TMBalance p where p.company=:company AND p.employment=:employment AND LOWER(p.module)=LOWER(:module) AND LOWER(p.type)=LOWER(:type)")
List<TMBalance> findBalanceTypeByEmployment(@Param("company") Long company, @Param("employment")  Long employment,  @Param("module") String module, @Param("type") String type);


@Query("select p from TMBalance p where p.company=:company AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND p.employment=:employment")
List<TMBalance> findByCompanyModuleCategoryType(@Param("company") Long company,@Param("module") String module, @Param("categoryType") String categoryType,@Param("employment") Long employment);


@Query("select p from TMBalance p where p.company=:company AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND LOWER(p.type)=LOWER(:type) AND p.employment=:employment")
List<TMBalance> findByCompanyModuleCategoryTypeType(@Param("company") Long company,@Param("module") String module, @Param("categoryType") String categoryType, @Param("type") String type, @Param("employment") Long employment);


@Query("SELECT DISTINCT categoryTypeId, categoryType  FROM TMBalance where company=:company AND employment=:employment AND module=:module")
List<Object[]> findListCategoryName(@Param("company") Long company , @Param("employment") Long employment, @Param("module") String module);

//
//@Query("select p from TMBalance p where p.company=:company AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND p.employment=:employment")
//List<TMBalance> findCompanyEmploymentModuleCategoryType(@Param("company") Long company,@Param("module") String module, @Param("categoryType") String categoryType,@Param("employment") Long employment);
//

@Modifying
@Query("UPDATE TMBalance set needSync=true where id=:id")
void approved(@Param("id") Long id);

}
