package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Pattern;

@Repository
public interface PatternRepository extends PagingAndSortingRepository<Pattern, Long>{
	
	@Query
	Pattern findByExtId(String extId);
	
	@Query("SELECT u FROM Pattern u WHERE u.needSync=true")
	List<Pattern> findNeedSync();
	
	@Query("SELECT u FROM Pattern u WHERE u.lookupField=:lookupField order by patternNo asc")
	List<Pattern> findByLookupField(@Param("lookupField") String lookupField);
}
