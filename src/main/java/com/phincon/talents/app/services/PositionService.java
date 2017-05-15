package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PositionRepository;
import com.phincon.talents.app.model.hr.Position;

@Service
public class PositionService {
	
	@Autowired
	PositionRepository positionRepository;
	
	@Transactional
	public Position findPosition(Long id){
		return positionRepository.findOne(id);
	}
	
	@Transactional
	public Position findByExtId(String extId){
		return positionRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Position> findAll(){
		return positionRepository.findAll();
	}
	
	@Transactional
	public Iterable<Position> findAllExtIdNull(){
		return positionRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		positionRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid){
		positionRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void save(Position obj){
		positionRepository.save(obj);
	}
	
}
