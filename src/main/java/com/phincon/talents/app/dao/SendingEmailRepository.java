package com.phincon.talents.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.phincon.talents.app.model.SendingEmail;

@Repository
public interface SendingEmailRepository extends PagingAndSortingRepository<SendingEmail,Long>{
	
}
