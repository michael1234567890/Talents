package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Position;

@Repository
public interface PositionRepository extends PagingAndSortingRepository<Position, Long>{
	@Query
	Position findByExtId(String extId);
	
	@Query("SELECT u FROM Position u WHERE u.extId is NULL")
	List<Position> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Position SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Position SET extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);
}
