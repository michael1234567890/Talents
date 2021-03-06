package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.TemplateMail;
import com.phincon.talents.app.model.hr.Position;

@Repository
public interface TemplateMailRepository extends PagingAndSortingRepository<TemplateMail, Long>{

	@Query
	List<TemplateMail> findByCompanyAndCode(Long company, String code);
	
	
}
