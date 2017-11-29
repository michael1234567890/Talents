package com.phincon.talents.app.controllers.api;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.PasswordResetTokenRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.dto.ResetPasswordDTO;
import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.PasswordResetToken;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.CompanyService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.PasswordResetTokenService;
import com.phincon.talents.app.services.UserSecurityService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.PasswordValidator;

@Controller
@RequestMapping("api")
public class PublicRestApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Autowired
	private CompanySettingsRepository companySettingsRepository;
	
	@Autowired
	VwEmpAssignmentRepository assignmentRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messages;
	
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> register(
			@RequestBody UserInfoDTO userInfo) {
		// get request body
		if (!userInfo.getPassword().equals(userInfo.getRepassword())) {
			throw new CustomException(
					"Error : Password and Confirm Password is not same");
		}

		// cek company code is registered or not
		Company company = companyService.findByCode(userInfo.getCompanyCode());
		if (company == null) {
			throw new CustomException("Error : company code is not registered");
		}

		// Load company Settings
		List<CompanySettings> listCompanySettings = companySettingsRepository
				.findByCompany(company.getId());
		CompanySettings companySettings = null;
		if (listCompanySettings != null && listCompanySettings.size() > 0) {
			companySettings = listCompanySettings.get(0);
			// checking password policy (regex)
			if (companySettings.getIsRegexPasswordActive()
					&& companySettings.getRegexPassword() != null
					&& !companySettings.getRegexPassword().equals("")) {
				PasswordValidator passwordValidator = new PasswordValidator(
						companySettings.getRegexPassword());
				if (!passwordValidator.validate(userInfo.getPassword())) {
					throw new CustomException(
							"Error : Your password must contains "
									+ companySettings
											.getMsgErrorRegexPassword());
				}
			}
		}

		// check mail and company code in employee repo
		Employee employee = employeeService
				.findByWorkEmail(userInfo.getEmail());
		if (employee == null) {
			throw new CustomException(
					"Error : Employee not registered with this email");
		}

		if (employee.getCompany() != company.getId()) {
			throw new CustomException(
					"Error : Employee and Company code does not match");
		}
		
		// check email already register or not
		User checkUser = userService.findByEmail(userInfo.getEmail());
		if (checkUser != null) {
			// send message user already registered with this email
			throw new CustomException(
					"Error : This email is already registered please choose another one");
		}
		
		userInfo.setLeader(false);
		List<VwEmpAssignment> employeeAssignment = assignmentRepository.findByDirectEmployee(
				employee.getId());
		
		if(employeeAssignment != null && employeeAssignment.size() > 0)
			userInfo.setLeader(true);
		
		userInfo.setAdmin(false);
		userInfo.setEmployeeId(employee.getId());
		userInfo.setFirstName(employee.getFirstName());
		userInfo.setLastName(employee.getLastName());
		userInfo.setCompany(company.getId());
		userInfo.setEmployeeExtId(employee.getExtId());
		userInfo.setFullName(employee.getName());
		userService.createUser(userInfo);

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Registration Successfully. Please Check your email", false),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> resetPassword(final HttpServletRequest request,
			@RequestBody Map<String, Object> map) {
		String email = (String) map.get("email");
		User user = userRepository.findByUsernameCaseInsensitive(email);
		if (user == null) {
			throw new CustomException("This email " + email
					+ " is not registed. Please try again");
		}
		
		// check berapa kali hari ini reset password
		List<PasswordResetToken> listPasswordResetToken = passwordTokenRepository.findByUserAndToday(user,new Date());
		if(listPasswordResetToken != null && listPasswordResetToken.size() > 2) {
			throw new CustomException("Reset password only 3 times per Day.");
		}
		
		
		String token = UUID.randomUUID().toString();
		passwordResetTokenService.createPasswordResetTokenForUser(user, token);
		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"An email has been sent to " + email
						+ " for create a new password", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actionresetpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> actionResetpassword(@RequestBody ResetPasswordDTO resetPassword) {
	
		if(resetPassword.getUserId() == null || resetPassword.getToken() == null) {
			throw new CustomException("Param ID and Token is required");
		}
		
		PasswordResetToken passwordResetToken = passwordResetTokenService.findByTokenAndActive(resetPassword.getUserId(), resetPassword.getToken());
		if (passwordResetToken == null) {
			throw new CustomException("Your Token is invalid or expired");
		}
		
	
		
		passwordResetTokenService.actionResetPassword(resetPassword,passwordResetToken);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your new password has been created.", false), HttpStatus.OK);
	}
	
	

	private SimpleMailMessage constructResetTokenEmail(
			final String contextPath, final Locale locale, final String token,
			final User user) {
		final String url = env.getProperty("url.resetpassword")+"/"
				+ user.getId() + "/" + token;
		final String message = messages.getMessage("message.resetPassword",
				null, locale);
		return constructEmail("Reset Password", message + " \r\n" + url, user);
	}

	private SimpleMailMessage constructEmail(String subject, String body,
			User user) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

}
