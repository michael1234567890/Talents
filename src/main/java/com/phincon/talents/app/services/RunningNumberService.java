package com.phincon.talents.app.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.RunningNumberRepository;
import com.phincon.talents.app.model.RunningNumber;

/**
 *
 * Auto Generate number based on prefix and company
 *
 */
@Service
public class RunningNumberService {

	@Autowired
	RunningNumberRepository runningNumberRepository;

	@Transactional
	public String getLastNumber(Long company,Date today, String prefix, String empNo) {
		String runningNumberCode = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		String month = cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1);
		Long lastNumber = 1L;
		List<RunningNumber> listRunningNumbers = runningNumberRepository.findByCompanyAndMonthAndPrefix(company, month, prefix);
		RunningNumber objRunningNumber = null;
		if(listRunningNumbers != null && listRunningNumbers.size() > 0) {
			objRunningNumber = listRunningNumbers.get(0);
		}
		
		if(objRunningNumber != null ) {
			lastNumber =objRunningNumber.getLastNumber()+ 1L;
			runningNumberCode = prefix+company + month.replace("-", "") + lastNumber;
			objRunningNumber.setLastNumber(lastNumber);
		}else {
			runningNumberCode = prefix+company + month.replace("-", "") + lastNumber;
			objRunningNumber = new RunningNumber();
			objRunningNumber.setCompany(company);
		//	objRunningNumber.setCustomerCode(customerCode);
			objRunningNumber.setDate(new Date());
			objRunningNumber.setMonth(month);
			objRunningNumber.setLastNumber(lastNumber);
			objRunningNumber.setPrefix(prefix);
		}
		runningNumberRepository.save(objRunningNumber);
		
		return runningNumberCode;
	}

}
