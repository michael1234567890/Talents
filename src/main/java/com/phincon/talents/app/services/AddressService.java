package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.AddressRepository;
import com.phincon.talents.app.dao.AddressTempRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.AddressTemp;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	AddressTempRepository addressTempRepository;

	
	
	@Transactional
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}
	
	@Transactional
	public void approvedChangeAddress(DataApproval dataApproval) {
		Long addressId = dataApproval.getObjectRef();
		addressRepository.approvedChangeAddress(addressId);
	}

	@Transactional
	public void save(Address address) {
		addressRepository.save(address);
	}
	
	public Iterable<Address> findByEmployee(Long employee){
		return addressRepository.findByEmployee(employee);
	}
	
	public void rejected(DataApproval dataApproval) {
		Address address = addressRepository.findOne(dataApproval.getObjectRef());
		if(address != null)
			addressRepository.delete(address);
	}
	
	@Transactional
	public void approvedSubmitAddress(DataApproval dataApproval) {
		Long addressId = dataApproval.getObjectRef();
		addressRepository.approvedSubmitAddress(addressId);
	}

	public Address findById(Long id) {
		return addressRepository.findOne(id);
	}

	public void rejectedChange(DataApproval dataApproval) {
		Address address = addressRepository.findOne(dataApproval.getObjectRef());
		if(address!= null){
			AddressTemp addressTemp =addressTempRepository.findOne(address.getAddressTemp());
			if(addressTemp != null) {
				address = copyFromAddressTemp(address, addressTemp);
				address.setNeedSync(false);
				address.setAddressTemp(null);
				address.setStatus(null);
				addressRepository.save(address);
			}
			
		}
	}

	private Address copyFromAddressTemp(Address address, AddressTemp addressTemp) {
		
		address.setAddress(addressTemp.getAddress());
		address.setAddressStatus(addressTemp.getAddressStatus());
		address.setCity(addressTemp.getCity());
		address.setDistance(addressTemp.getDistrict());
		address.setProvince(addressTemp.getProvince());
		address.setCountry(addressTemp.getCountry());
		address.setRt(addressTemp.getRt());
		address.setRw(addressTemp.getRw());
		address.setPhone(addressTemp.getPhone());
		address.setResidence(addressTemp.getResidence());
		address.setZipCode(addressTemp.getZipCode());
		address.setStayStatus(addressTemp.getStayStatus());
		
		return address;
	}
}
