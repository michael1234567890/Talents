package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.dto.TotalCategoryDTO;
import com.phincon.talents.app.model.hr.TMRequest;
import com.phincon.talents.app.model.hr.TMRequestHeader;

@Repository
public interface TMRequestHeaderRepository extends
		PagingAndSortingRepository<TMRequestHeader, Long> {

	@Modifying
	@Query("UPDATE TMRequestHeader set status='" + TMRequestHeader.APPROVED
			+ "' where id=:id")
	void approved(@Param("id") Long id);

	@Modifying
	@Query("UPDATE TMRequestHeader set needReport=:needReport where id=:id")
	void updateByNeedReportAndId(@Param("needReport") boolean needReport,
			@Param("id") Long id);

	@Modifying
	@Query("UPDATE TMRequestHeader set status='" + TMRequestHeader.REJECTED
			+ "' where id=:id")
	void rejected(@Param("id") Long id);

	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployee(
			@Param("company") Long company, @Param("employee") Long employee);
	
	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee AND u.status=:status ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployeeAndStatus(
			@Param("company") Long company, @Param("employee") Long employee, @Param("status") String status);
	
	
	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.reqNo like %:reqNo% ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndRequestNoLike(
			@Param("company") Long company, @Param("reqNo") String reqNo);

	

	@Query("SELECT u FROM TMRequestHeader u WHERE u.employee=:employee AND u.id=:id")
	TMRequestHeader findByEmployeeAndId(@Param("employee") Long employee,
			@Param("id") Long id);

	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee  AND u.needReport=TRUE ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployeeAndNeedReport(
			@Param("company") Long company, @Param("employee") Long employee);

	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee AND u.module=:module ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployeeAndModule(
			@Param("company") Long company, @Param("employee") Long employee,
			@Param("module") String module);
	
	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee AND u.module=:module AND u.status=:status ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployeeAndModuleAndStatus(
			@Param("company") Long company, @Param("employee") Long employee,
			@Param("module") String module,@Param("status") String status);

	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND LOWER(u.reqNo) like lower(concat('%', :reqNo,'%')) AND LOWER(u.module)=LOWER(:module) ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndModuleAndRequestNoLike(
			@Param("company") Long company, @Param("reqNo") String reqNo,
			@Param("module") String module);

	
	@Query("SELECT u FROM TMRequestHeader u WHERE u.company=:company AND u.employee=:employee AND u.module=:module AND u.needReport=TRUE AND u.status ='"
			+ TMRequest.APPROVED + "' ORDER BY createdDate DESC")
	List<TMRequestHeader> findByCompanyAndEmployeeAndModuleAndNeedReport(
			@Param("company") Long company, @Param("employee") Long employee,
			@Param("module") String module);

	@Query("select p from TMRequestHeader p where p.company=:company AND p.requester=:requester AND LOWER(p.module)=LOWER(:module) AND LOWER(p.categoryType)=LOWER(:categoryType) AND p.startDate <=:startDate AND p.endDate >=:startDate AND LOWER(p.status)!='rejected'")
	List<TMRequestHeader> findBetweenStartEndDate(
			@Param("company") Long company, @Param("requester") Long requester,
			@Param("module") String module,
			@Param("categoryType") String categoryType,
			@Param("startDate") Date startDate);

	@Modifying
	@Query("UPDATE TMRequestHeader set totalAmount=:amount, totalAmountSubmit=:amount where id=:id")
	void updateTotalAmountById(@Param("amount") Double amount,
			@Param("id") Long id);
	
	@Query("select new com.phincon.talents.app.dto.TotalCategoryDTO(sum(u.totalAmount), u.module ,u.categoryType, u.company,u.employee) from TMRequestHeader u where u.company=:company AND u.employee=:employee AND LOWER(module)=LOWER(:module) AND u.startDate >= :fromDate AND u.startDate <= :toDate GROUP BY u.company,u.employee,u.module,u.categoryType")
	List<TotalCategoryDTO> sumTotalAmountByModuleAndCategoryAndRangeDate(@Param("company") Long company , @Param("employee") Long employee, @Param("module") String module, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
	
	@Query("select new com.phincon.talents.app.dto.TotalCategoryDTO(sum(u.totalAmount), u.module ,u.categoryType, u.company,u.employee) from TMRequestHeader u where u.company=:company AND u.employee=:employee AND u.startDate >= :fromDate AND u.startDate <= :toDate GROUP BY u.company,u.employee,u.module,u.categoryType")
	List<TotalCategoryDTO> sumTotalAmountByCategoryAndRangeDate(@Param("company") Long company , @Param("employee") Long employee, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
	
	

}
