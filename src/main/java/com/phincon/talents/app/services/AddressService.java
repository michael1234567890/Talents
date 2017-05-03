package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.AddressRepository;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Employee;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	
	
	@Transactional
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}

	@Transactional
	public void save(Address address) {
		addressRepository.save(address);
	}
	
	public Iterable<Address> findByEmployee(Employee employee){
		return addressRepository.findByEmployee(employee);
	}
}
