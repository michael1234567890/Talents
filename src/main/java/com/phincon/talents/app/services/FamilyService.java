package com.phincon.talents.app.services;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.controllers.api.FamilyRestApi;
import com.phincon.talents.app.dao.DataApprovalRepository;
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
	
	@Autowired
	DataApprovalRepository dataApprovalRepository;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
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

	public Iterable<Family> findByEmployeeAndIsEligibleKacamata(Long employeeId, Boolean isEligibleKacamata){
		return familyRepository.findByEmployeeAndIsEligibleKacamata(employeeId, isEligibleKacamata);
	}
	
	public Iterable<Family> findByEmployeeAndIsEligibleMedical(Long employeeId, Boolean isEligibleMedical){
		return familyRepository.findByEmployeeAndIsEligibleMedical(employeeId, isEligibleMedical);
	}
	
	@Transactional
	public Iterable<Family> getFamilyEligible(Long employeeId){
		return familyRepository.getFamilyEligible(employeeId);
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
			
			FamilyTemp familyTemp = new FamilyTemp();
			familyTemp = copyFamilyToTemp(family,familyTemp);
			familyTempRepository.save(familyTemp);
			
			 // delete family object
			familyRepository.delete(family);
			
			// change Data Approval
			dataApproval.setObjectName(FamilyTemp.class.getSimpleName());
			dataApproval.setObjectRef(familyTemp.getId());
		}
		
	}
	
	public void rejectedChange(DataApproval dataApproval) {
		Family family = familyRepository.findOne(dataApproval.getObjectRef());
		String dataFamily = null;
		try {
			 dataFamily = this.objectMapper.writeValueAsString(family);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Family family1 = family;
		if(family!= null){
			FamilyTemp familyTemp = familyTempRepository.findOne(family.getFamilyTemp());
			if(familyTemp != null) {
				family = copyFromFamilyTemp(family, familyTemp);
				family.setNeedSync(false);
				family.setFamilyTemp(null);
				family.setStatus(null);
				familyRepository.save(family);
				familyTemp = copyFamilyToTemp(family1, familyTemp);
				familyTempRepository.save(familyTemp);
				// change Data Approval
				dataApproval.setObjectName(FamilyTemp.class.getSimpleName());
				dataApproval.setObjectRef(familyTemp.getId());
				dataApproval.setData(dataFamily);
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
		
		family.setNircNo(familyTemp.getNircNo());
		family.setFamilyCardNo(familyTemp.getFamilyCardNo());
		family.setDistrict(familyTemp.getDistrict());
		family.setSubDistrict(familyTemp.getSubDistrict());
		family.setRt(familyTemp.getRt());
		family.setRw(familyTemp.getRw());
		family.setNationality(familyTemp.getNationality());
		family.setAssuranceName(familyTemp.getAssuranceName());
		family.setPolisNo(familyTemp.getPolisNo());
		family.setNpwpNo(familyTemp.getNpwpNo());
		family.setPassportNo(familyTemp.getPassportNo());
		family.setZipCode(familyTemp.getZipCode());
		return family;
	}
	
	private FamilyTemp copyFamilyToTemp(Family family , FamilyTemp familyTemp) {
		
		familyTemp.setAddress(family.getAddress());
		familyTemp.setBirthPlace(family.getBirthPlace());
		familyTemp.setBirthDate(family.getBirthDate());
		familyTemp.setBloodType(family.getBloodType());
		// familyTemp.setfamily.getEmail()
		familyTemp.setName(family.getName());
	
		familyTemp.setPhone(family.getPhone());
		familyTemp.setRelationship(family.getRelationship());
		familyTemp.setEmployee(family.getEmployee());
		familyTemp.setGender(family.getGender());
		familyTemp.setOccupation(family.getOccupation());
		familyTemp.setMaritalStatus(family.getMaritalStatus());
		
		familyTemp.setNircNo(family.getNircNo());
		familyTemp.setFamilyCardNo(family.getFamilyCardNo());
		familyTemp.setDistrict(family.getDistrict());
		familyTemp.setSubDistrict(family.getSubDistrict());
		familyTemp.setRt(family.getRt());
		familyTemp.setRw(family.getRw());
		familyTemp.setNationality(family.getNationality());
		familyTemp.setAssuranceName(family.getAssuranceName());
		familyTemp.setPolisNo(family.getPolisNo());
		familyTemp.setNpwpNo(family.getNpwpNo());
		familyTemp.setPassportNo(family.getPassportNo());
		familyTemp.setZipCode(family.getZipCode());
		
		familyTemp.setEmail(family.getEmail());
		
		return familyTemp;
	}
}
