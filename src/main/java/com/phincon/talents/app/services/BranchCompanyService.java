package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.BranchCompanyRepository;
import com.phincon.talents.app.model.BranchCompany;

@Service
public class BranchCompanyService {
	
	@Autowired
	BranchCompanyRepository branchCompanyRepository;
	
	@Transactional
	public BranchCompany findById(Long id){
		return branchCompanyRepository.findOne(id);
	}
	
	@Transactional
	Iterable<BranchCompany> findAll(){
		return branchCompanyRepository.findAll();
	}
	
	@Transactional
	public Iterable<BranchCompany> findByCompany(Long id){
		return branchCompanyRepository.findByCompany(id);
	}
	
	@Transactional
	public void save(BranchCompany obj){
		branchCompanyRepository.save(obj);
	}
	
	
}
