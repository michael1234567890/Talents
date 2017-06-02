package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PayrollElementDetailYearlyRepository;
import com.phincon.talents.app.dao.PayrollElementHeaderYearlyRepository;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.PayrollElementDetailYearly;
import com.phincon.talents.app.model.hr.PayrollElementHeaderYearly;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementHeaderYearlyService {

	@Autowired
	PayrollElementHeaderYearlyRepository payrollElementHeaderRepository;
	
	@Autowired
	PayrollElementDetailYearlyRepository payrollElementDetailRepository;
	
	@Autowired
	EmployeeService employeeService;

	
	
	@Transactional
	public Iterable<PayrollElementHeaderYearly> findAll() {
		return payrollElementHeaderRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementHeaderYearly obj) {
		payrollElementHeaderRepository.save(obj);
	}
	
	@Transactional
	public PayrollElementHeaderYearly findByExtId(String extId) {
		return payrollElementHeaderRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<PayrollElementHeaderYearly> findByEmploymentAndYearAndEmployee(Long employment, String year, Long employee) {
		List<PayrollElementHeaderYearly> listElementHeaders = null;
		
		listElementHeaders = payrollElementHeaderRepository
				.findByEmploymentAndCurrentYear(employment, year);
	
		List<PayrollElementHeaderYearly> listElementHeadersResult = new ArrayList<PayrollElementHeaderYearly>();
		if (listElementHeaders != null && listElementHeaders.size() > 0) {
			// get detail by in and out and header
			for (PayrollElementHeaderYearly payrollElementHeader : listElementHeaders) {
				PayrollElementHeaderYearly obj = payrollElementHeader;
				Long headerId = obj.getId();
				List<PayrollElementDetailYearly> payrollElemenDetailListIn = payrollElementDetailRepository.findByPayrollElementHeaderAndElementType(headerId, PayrollElementDetailYearly.ELEMENT_TYPE_ALLOWANCE);
				obj.setDetailIn(payrollElemenDetailListIn);
				List<PayrollElementDetailYearly> payrollElemenDetailListOut = payrollElementDetailRepository.findByPayrollElementHeaderAndElementType(headerId, PayrollElementDetailYearly.ELEMENT_TYPE_DEDUCTION);
				obj.setDetailOut(payrollElemenDetailListOut);
				listElementHeadersResult.add(obj);
				Employee employeeTransient = employeeService.findEmployeeWithAssignment(employee);
				obj.setEmployeeTransient(employeeTransient);
			}

		}
		return listElementHeadersResult;
	}
	
}
