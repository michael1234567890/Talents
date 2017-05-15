package com.phincon.talents.app.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.FamilyHistRepository;
import com.phincon.talents.app.dao.FamilyRepository;
import com.phincon.talents.app.dao.FamilyTempRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.FamilyHist;
import com.phincon.talents.app.model.hr.FamilyTemp;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class FamilyService {

	@Autowired
	FamilyRepository familyRepository;

	@Autowired
	FamilyHistRepository familyHistRepository;
	
	@Autowired
	FamilyTempRepository familyTempRepository;
	
	@Transactional
	public Iterable<Family> findAll() {
		return familyRepository.findAll();
	}
	
	@Transactional
	public Family findById(Long id) {
		return familyRepository.findOne(id);
	}

	@Transactional
	public void save(Family family) {
		familyRepository.save(family);
	}
	
	public Iterable<Family> findByEmployee(Long employeeId){
		return familyRepository.findByEmployee(employeeId);
	}

	@Transactional
	public void approvedSubmitFamily(DataApproval dataApproval) {
		Long familyId = dataApproval.getObjectRef();
		familyRepository.approvedSubmitFamily(familyId);
	}
	
	@Transactional
	public void approvedChangeFamily(DataApproval dataApproval) {
		Long familyId = dataApproval.getObjectRef();
		familyRepository.approvedChangeFamily(familyId);
	}

	public void rejected(DataApproval dataApproval) {
		// get family object
		Family family = familyRepository.findOne(dataApproval.getObjectRef());
		// add family hist
		if(family != null) {
			FamilyHist familyHist = new FamilyHist();
			familyHist.setFamilyIdRef(family.getId());
			familyHist.setAddress(family.getAddress());
			familyHist.setAliveStatus(family.getAliveStatus());
			familyHist.setBirthDate(family.getBirthDate());
			familyHist.setBirthPlace(family.getBirthPlace());
			familyHist.setBloodType(family.getBloodType());
			familyHist.setCreatedBy(family.getCreatedBy());
			familyHist.setCreatedDate(family.getCreatedDate());
			familyHist.setDeceaseDate(family.getDeceaseDate());
			familyHist.setDependent(family.getDependent());
			familyHist.setDependent(family.getDependent());
			familyHist.setEmployee(family.getEmployee());
			familyHist.setExtId(family.getExtId());
			familyHist.setGender(family.getGender());
			familyHist.setLastEducation(family.getLastEducation());
			familyHist.setLetterNo(family.getLetterNo());
			familyHist.setMaritalStatus(family.getMaritalStatus());
			familyHist.setMedicalStatus(family.getMedicalStatus());
			familyHist.setModifiedBy(family.getModifiedBy());
			familyHist.setModifiedDate(new Date());
			familyHist.setName(family.getName());
			familyHist.setOccupation(family.getOccupation());
			familyHist.setPhone(family.getPhone());
			familyHist.setRelationship(family.getRelationship());
			familyHist.setStatus(family.getStatus());
			
			familyHistRepository.save(familyHist);
			 // delete family object
			familyRepository.delete(family);
		}
		
	}
	
	public void rejectedChange(DataApproval dataApproval) {
		Family family = familyRepository.findOne(dataApproval.getObjectRef());
		if(family!= null){
			FamilyTemp familyTemp = familyTempRepository.findOne(family.getFamilyTemp());
			if(familyTemp != null) {
				family = copyFromFamilyTemp(family, familyTemp);
				family.setNeedSync(false);
				family.setFamilyTemp(null);
				family.setStatus(null);
				familyRepository.save(family);
			}
			
		}
		
	}
	
	private Family copyFromFamilyTemp(Family family , FamilyTemp familyTemp) {
		
		family.setAddress(familyTemp.getAddress());
		family.setBirthPlace(familyTemp.getBirthPlace());
		family.setBirthDate(familyTemp.getBirthDate());
		family.setBloodType(familyTemp.getBloodType());
		// familyTemp.setfamily.getEmail()
		family.setName(familyTemp.getName());
		family.setPhone(familyTemp.getPhone());
		family.setRelationship(familyTemp.getRelationship());
		family.setEmployee(familyTemp.getEmployee());
		family.setGender(familyTemp.getGender());
		family.setOccupation(familyTemp.getOccupation());
		family.setMaritalStatus(familyTemp.getMaritalStatus());
		return family;
	}
}
