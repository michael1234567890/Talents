package com.phincon.talents.app.controllers.api.user;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.EmployeeDisablePayslipRepository;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.PayrollElementHeaderRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.PayrollRequestDTO;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.EmployeeDisablePayslip;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.PayrollElementHeader;
import com.phincon.talents.app.model.hr.PayrollElementHeaderYearly;
import com.phincon.talents.app.services.PayrollElementHeaderService;
import com.phincon.talents.app.services.PayrollElementHeaderYearlyService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class PayrollController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	@Autowired
	EmployeeDisablePayslipRepository employeeDisablePayslipRepository;

	@Autowired
	PayrollElementHeaderRepository payrollElementHeaderRepository;

	@Autowired
	PayrollElementHeaderYearlyService payrollElementHeaderYearlyService;
	
	@Autowired
	PayrollElementHeaderService payrollElementHeaderService;
	
	@Autowired
	CompanySettingsRepository companySettingsRepository;

	
	@RequestMapping(value = "/user/payroll", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<PayrollElementHeader>> getPayroll(
			@RequestBody PayrollRequestDTO request,
			OAuth2Authentication authentication) {
				
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<CompanySettings> listCompany = companySettingsRepository.findByCompany(user.getCompany());
		CompanySettings companySettings = null;
		if(listCompany != null && listCompany.size() > 0)
			companySettings = listCompany.get(0);
		
		// temporary
		if(companySettings.getIsPayrollMonthDisable() != null && companySettings.getIsPayrollMonthDisable())
			throw new CustomException("This Feature Will Available Soon");
				
		// load company setting
		List<CompanySettings> listCompanySettings = companySettingsRepository.findByCompany(user.getCompany());
		CompanySettings companySetting = null;
		if(listCompanySettings!=null && listCompanySettings.size() > 0) 
			companySetting = listCompanySettings.get(0);
		
//		if(user.getCompany() == 1 && (user.getIsChangePassword() == null || !user.getIsChangePassword()))
//			throw new CustomException("Please change your password to see your payslip.");
		
		// get employment ID with user
		
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		if(listEmployment == null && listEmployment.size() == 0)
			throw new CustomException("Your Employment ID is not Found.");
		
		String month = null;

		Employment employment = listEmployment.get(0);
		
		// check is employee can not see payslip
		List<EmployeeDisablePayslip> listEmpDisablePayslip = employeeDisablePayslipRepository.findByCompanyAndEmployeeNo(user.getCompany(), employment.getName());
		if(listEmpDisablePayslip != null && listEmpDisablePayslip.size() > 0) {
			throw new CustomException("You are not eligible to see payslip. Please contact HC-PCBM for more info.");
		}
		
		if (request.getPayrollType() == null) {
			throw new CustomException("Payroll type can not be empty.");
		}
		
		if (request.getPayrollType().equals(PayrollRequestDTO.PY_TYPE_LATEST)) {
			
		} else if (request.getPayrollType().equals(PayrollRequestDTO.PY_TYPE_MONTH)) {
			if(request.getYear() == null || request.getMonth() == null)
				throw new CustomException("Year and Month can't be empty.");
			month = request.getYear() + "-" + request.getMonth();
			validationBasedOnDayOfPayroll(companySetting,month, new Date());
			validationThreeMonth(employment.getId(), month);
			
		} else {
			throw new CustomException("Invalid value of payroll type.");
		}
		
		List<PayrollElementHeader> listElementHeaders = payrollElementHeaderService
				.findByMonthAndEmployee(request.getPayrollType(), month, employment.getId(), user.getEmployee());
		return new ResponseEntity<List<PayrollElementHeader>>(
				listElementHeaders, HttpStatus.OK);

	}
	
	 
	
	private void validationBasedOnDayOfPayroll(CompanySettings companySetting, String month, Date requestDate) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(requestDate);
		if(companySetting.getDayOfPayroll() != null) {
			String[] arrStrDate = strDate.split("-");
			String currentMonth = arrStrDate[0] + "-"+ arrStrDate[1];
			if(currentMonth.equals(month)) {
				String strRefDate = currentMonth+"-"+companySetting.getDayOfPayroll();
				Date refDate = Utils.convertStringToDate(strRefDate);
				if(Utils.diffDayInt(refDate, requestDate) < 0) {
					throw new CustomException("Sorry, you can't see the payslip yet.");
				}
			}
		}
		
	}

	private void validationThreeMonth(Long employmentId, String month){
		List<PayrollElementHeader> listElementHeaders = payrollElementHeaderRepository
				.findByLatestMonthAndEmployment(employmentId, new PageRequest(0, 1));
		if(listElementHeaders != null && listElementHeaders.size() > 0){

			PayrollElementHeader payrollElementHeader = listElementHeaders.get(0);
			if(payrollElementHeader.getPeriodDate()!= null) {
				String strRequestPayrollDate =month  + "-01";
				// implement validation only latest 3 month 
				Date lastPayrollDate = Utils.convertStringToDate(payrollElementHeader.getPeriodDate());
				Date currentPayrollRequest = Utils.convertStringToDate(strRequestPayrollDate);
				int diffMonth = Utils.diffMonth(currentPayrollRequest, lastPayrollDate);
				if(diffMonth < 0 || diffMonth > 2) {
					throw new CustomException("You could only see the latest 3 months. Please change the requested Month.");
				}
			}
		}else {
			throw new CustomException("Payroll Data Not Found");
		}
	}
	
	@RequestMapping(value = "/user/payroll/yearly", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<PayrollElementHeaderYearly>> getPayrollYearly(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<CompanySettings> listCompany = companySettingsRepository.findByCompany(user.getCompany());
		CompanySettings companySettings = null;
		if(listCompany != null && listCompany.size() > 0)
			companySettings = listCompany.get(0);
		
		if(companySettings.getIsPayrollYearDisable() != null && companySettings.getIsPayrollYearDisable())
			throw new CustomException("This Feature Will Available Soon");
			
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy");
//		String year = df.format(new Date());
		
		String year = null;
		if(companySettings.getPayslipYearlyYear()== null || companySettings.getPayslipYearlyYear().equals("")) {
			throw new CustomException("No year parameter selected. Please contact Admin.");
		}
		year = companySettings.getPayslipYearlyYear();
		// get employment ID with user
		
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		if(listEmployment == null && listEmployment.size() == 0)
			throw new CustomException("Your Employment ID is not Found.");
		
		
		Employment employment = listEmployment.get(0);
		
		List<PayrollElementHeaderYearly> listElementHeaderYearly = payrollElementHeaderYearlyService.findByEmploymentAndYearAndEmployee(employment.getId(), year, user.getEmployee());
				
		return new ResponseEntity<List<PayrollElementHeaderYearly>>(
				listElementHeaderYearly, HttpStatus.OK);
	}
	
	/*
	 * get latest period payroll employee
	 */
	@RequestMapping(value = "/user/payroll/latestperiod", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getLatestPeriod(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		if(listEmployment == null && listEmployment.size() == 0)
			throw new CustomException("Your Employment ID is not Found.");
		Employment employment = listEmployment.get(0);
		Map<String, Object> map = null;
		List<Object[]> listObject = payrollElementHeaderRepository.latestPeriodDate(employment.getId(), new PageRequest(0, 1));
		if(listObject!= null && listObject.size() > 0) {
			map = new HashMap<String, Object>();
			Object object = listObject.get(0);
			String periodDate = (String) object;
			map.put("periodDate", periodDate);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

}
