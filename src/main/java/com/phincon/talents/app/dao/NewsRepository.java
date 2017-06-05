package com.phincon.talents.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.phincon.talents.app.model.hr.News;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
	
	@Query("SELECT u FROM News u WHERE u.company = :company and u.publishedDate >= :publishedDate and u.endDate <= :endDate and u.active = true")
	List<News> getCurrentNews(@Param("company") Long company, @Param("publishedDate") Date publishedDate, @Param("endDate") Date endDate);
}
