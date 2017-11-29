package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.phincon.talents.app.model.hr.News;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
	
	@Query("SELECT u FROM News u WHERE u.company = :company and u.publishedDate <= :today and u.endDate >= :today and u.active = true")
	List<News> getCurrentNews(@Param("company") Long company, @Param("today") Date today);
	
	 @Query("SELECT COUNT(u.id) FROM News u WHERE u.company = :company and u.publishedDate <= :today and u.endDate >= :today and u.active = true")
	 List<Long> countCurrentNews(@Param("company") Long company, @Param("today") Date today);

}
