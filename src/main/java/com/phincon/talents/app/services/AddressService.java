package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
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
	
	private ObjectMapper objectMapper = new ObjectMapper();

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

	public Iterable<Address> findByEmployee(Long employee) {
		return addressRepository.findByEmployee(employee);
	}

	public Address findByAddressStatusAndEmployee(String addressStatus,
			Long employee) {
		Address address = null;
		List<Address> listAddress = addressRepository
				.findByAddressStatusAndEmployee(addressStatus, employee);
		if (listAddress != null && listAddress.size() > 0)
			address = listAddress.get(0);
		return address;
	}

	public void rejected(DataApproval dataApproval) {
		Address address = addressRepository
				.findOne(dataApproval.getObjectRef());
		if (address != null) {
			// copy address to address temp
			AddressTemp addressTemp = new AddressTemp();
			addressTemp  = copyAddressToTemp(address,addressTemp);
			addressTempRepository.save(addressTemp);
			dataApproval.setObjectName(AddressTemp.class.getSimpleName());
			dataApproval.setObjectRef(addressTemp.getId());
			// delete address
			addressRepository.delete(address);
		}

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
		Address address = addressRepository
				.findOne(dataApproval.getObjectRef());
		String dataAddress = null;
		try {
			dataAddress = this.objectMapper.writeValueAsString(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (address != null) {
			AddressTemp addressTemp = addressTempRepository.findOne(address
					.getAddressTemp());
			if (addressTemp != null) {
				Address addressChanged = address;
				address = copyFromAddressTemp(address, addressTemp);
				address.setNeedSync(false);
				address.setAddressTemp(null);
				address.setStatus(null);
				addressRepository.save(address);
				addressTemp = copyAddressToTemp(addressChanged, addressTemp);
				addressTempRepository.save(addressTemp);
				dataApproval.setObjectName(AddressTemp.class.getSimpleName());
				dataApproval.setObjectRef(addressTemp.getId());
				dataApproval.setData(dataAddress);
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

	private AddressTemp copyAddressToTemp(Address address, AddressTemp addressTemp) {

		addressTemp.setAddress(address.getAddress());
		addressTemp.setAddressStatus(address.getAddressStatus());
		addressTemp.setCity(address.getCity());
		addressTemp.setDistance(address.getDistrict());
		addressTemp.setProvince(address.getProvince());
		addressTemp.setCountry(address.getCountry());
		addressTemp.setRt(address.getRt());
		addressTemp.setRw(address.getRw());
		addressTemp.setPhone(address.getPhone());
		addressTemp.setResidence(address.getResidence());
		addressTemp.setZipCode(address.getZipCode());
		addressTemp.setStayStatus(address.getStayStatus());

		return addressTemp;
	}
}
