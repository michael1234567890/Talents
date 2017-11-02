package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PayrollElementDetailGroupRepository;
import com.phincon.talents.app.dao.PayrollElementHeaderRepository;
import com.phincon.talents.app.dto.PayrollRequestDTO;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.PayrollElementDetail;
import com.phincon.talents.app.model.hr.PayrollElementDetailGroup;
import com.phincon.talents.app.model.hr.PayrollElementHeader;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementHeaderService {
	
	

	@Autowired
	PayrollElementHeaderRepository payrollElementHeaderRepository;

	@Autowired
	PayrollElementDetailGroupRepository payrollElementDetailGroupRepository;
	
	@Autowired
	EmployeeService emplyeeService;

	@Transactional
	public Iterable<PayrollElementHeader> findAll() {
		return payrollElementHeaderRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementHeader obj) {
		payrollElementHeaderRepository.save(obj);
	}

	/*
	 * Show Employee payslip based on month OR Latest 
	 * input : type,month, employment
	 * output : List of PayrollHeader include PayrollDetail
	 */
	@Transactional
	public List<PayrollElementHeader> findByMonthAndEmployee(String type, String month,
			Long employment, Long employee) {
		List<PayrollElementHeader> listElementHeaders = null;
		if(type.equals(PayrollRequestDTO.PY_TYPE_LATEST)){
			listElementHeaders = payrollElementHeaderRepository
					.findByLatestMonthAndEmployment(employment, new PageRequest(0, 1));
		}else {
			listElementHeaders = payrollElementHeaderRepository
					.findByMonthPeriodAndEmployee("%" + month + "%", employment);
		}
		
		List<PayrollElementHeader> listElementHeadersResult = new ArrayList<PayrollElementHeader>();
		if (listElementHeaders != null && listElementHeaders.size() > 0) {
			// get detail by in and out and header
			for (PayrollElementHeader payrollElementHeader : listElementHeaders) {
				PayrollElementHeader obj = payrollElementHeader;
				Long headerId = obj.getId();
				List<PayrollElementDetailGroup> payrollElemenDetailListIn = payrollElementDetailGroupRepository.findByPayrollElementHeaderAndElementType(headerId, PayrollElementDetail.ELEMENT_TYPE_ALLOWANCE);
				if(payrollElemenDetailListIn == null)
					payrollElemenDetailListIn = new ArrayList<PayrollElementDetailGroup>();
				
				PayrollElementDetailGroup taxAllowance = new PayrollElementDetailGroup();
				taxAllowance.setElementGroup("Tunjangan Pajak");
				taxAllowance.setElementType("Allowance");
				taxAllowance.setTotal(payrollElementHeader.getTaxAllowance());
				
				if(taxAllowance.getTotal()!= null && taxAllowance.getTotal() != 0D)
					payrollElemenDetailListIn.add(taxAllowance);
				
				obj.setDetailIn(payrollElemenDetailListIn);
				List<PayrollElementDetailGroup> payrollElemenDetailListOut = payrollElementDetailGroupRepository.findByPayrollElementHeaderAndElementType(headerId, PayrollElementDetail.ELEMENT_TYPE_DEDUCTION);
				if(payrollElemenDetailListOut == null)
					payrollElemenDetailListOut = new ArrayList<PayrollElementDetailGroup>();
				
				PayrollElementDetailGroup taxDeduction = new PayrollElementDetailGroup();
				taxDeduction.setElementGroup("Potongan Pajak");
				taxDeduction.setElementType("Deduction");
				taxDeduction.setTotal(payrollElementHeader.getTaxGross() + payrollElementHeader.getTaxAllowance());
				
				if(taxDeduction.getTotal() != null && taxDeduction.getTotal()!=0D)
					payrollElemenDetailListOut.add(taxDeduction);
				
				
				PayrollElementDetailGroup taxPenalty = new PayrollElementDetailGroup();
				taxPenalty.setElementGroup("Penalti Pajak");
				taxPenalty.setElementType("Deduction");
				taxPenalty.setTotal(payrollElementHeader.getTaxPenalty());
				
				if(taxPenalty.getTotal() != null && taxPenalty.getTotal()!=0D)
					payrollElemenDetailListOut.add(taxPenalty);
				
				obj.setDetailOut(payrollElemenDetailListOut);
				listElementHeadersResult.add(obj);
				System.out.println("Employee ID " + employee);
				Employee employeeTransient = emplyeeService.findEmployeeWithAssignment(employee);
				obj.setEmployeeTransient(employeeTransient);
			}

		}
		return listElementHeadersResult;
	}

}
