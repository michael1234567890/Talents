package com.phincon.talents.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT u FROM Company u WHERE LOWER(u.name) = LOWER(:name)")
    Company findByNameCaseInsensitive(@Param("name") String username);

    @Query
    Company findByCode(String code);

}
