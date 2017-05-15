package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PayrollElementDetailRepository;
import com.phincon.talents.app.dao.PayrollElementHeaderRepository;
import com.phincon.talents.app.model.hr.PayrollElementDetail;
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
	PayrollElementDetailRepository payrollElementDetailRepository;

	@Transactional
	public Iterable<PayrollElementHeader> findAll() {
		return payrollElementHeaderRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementHeader obj) {
		payrollElementHeaderRepository.save(obj);
	}

	@Transactional
	public List<PayrollElementHeader> findByMonthAndEmployee(String month,
			Long user) {
		List<PayrollElementHeader> listElementHeaders = payrollElementHeaderRepository
				.findByMonthPeriodAndEmployee("%" + month + "%", user);
		List<PayrollElementHeader> listElementHeadersResult = new ArrayList<PayrollElementHeader>();
		if (listElementHeaders != null && listElementHeaders.size() > 0) {
			// get detail by in and out and header
			for (PayrollElementHeader payrollElementHeader : listElementHeaders) {
				PayrollElementHeader obj = payrollElementHeader;
				Long headerId = obj.getId();
				List<PayrollElementDetail> payrollElemenDetailListIn = payrollElementDetailRepository.findByPayrollElementHeaderAndElementType(headerId, "in");
				obj.setDetailIn(payrollElemenDetailListIn);
				List<PayrollElementDetail> payrollElemenDetailListOut = payrollElementDetailRepository.findByPayrollElementHeaderAndElementType(headerId, "out");
				obj.setDetailOut(payrollElemenDetailListOut);
				listElementHeadersResult.add(obj);
			}

		}
		return listElementHeadersResult;
	}

}
