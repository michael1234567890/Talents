package com.phincon.talents.app.controllers.api;

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

import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.ResetPasswordDTO;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.services.CompanyService;
import com.phincon.talents.app.services.EmployeeService;
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
	private CompanySettingsRepository companySettingsRepository;

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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> register(
			@RequestBody UserInfoDTO userInfo) {
		// get request body
		if (!userInfo.getPassword().equals(userInfo.getRepassword())) {
			throw new RuntimeException(
					"Error : Password and Confirm Password is not same");
		}

		// cek company code is registered or not
		Company company = companyService.findByCode(userInfo.getCompanyCode());
		if (company == null) {
			throw new RuntimeException("Error : company code is not registered");
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
					throw new RuntimeException(
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
			throw new RuntimeException(
					"Error : Employee not registered with this email");
		}

		if (employee.getCompany() != company.getId()) {
			throw new RuntimeException(
					"Error : Employee and Company code does not match");
		}
		// check email already register or not
		User checkUser = userService.findByEmail(userInfo.getEmail());
		if (checkUser != null) {
			// send message user already registered with this email
			throw new RuntimeException(
					"Error : This email is already registered please choose another one");
		}

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
			throw new RuntimeException("This email " + email
					+ " is not registed. Please try again");
		}
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"An email has been sent to " + email
						+ " for create a new password", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actionresetpassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> actionResetpassword(@RequestBody ResetPasswordDTO resetPassword) {
	
		if(resetPassword.getUserId() == null || resetPassword.getToken() == null) {
			throw new RuntimeException("Param ID and Token is required");
		}
		User user = userSecurityService.getUserFromPasswordResetToken(resetPassword.getUserId(), resetPassword.getToken());
		if (user == null) {
			throw new RuntimeException("Your Token is invalid or expired");
		}
		
		UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO();
		userChangePasswordDTO.setNewPassword(resetPassword.getNewPassword());
		userChangePasswordDTO.setConfirmPassword(resetPassword.getConfirmPassword());
		userService.changePassword(userChangePasswordDTO, user, false);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your new password has been created.", false), HttpStatus.OK);
	}
	
	

	private SimpleMailMessage constructResetTokenEmail(
			final String contextPath, final Locale locale, final String token,
			final User user) {
		final String url = env.getProperty("url.resetpassword")+"?id="
				+ user.getId() + "&token=" + token;
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
		System.out.println("From " + env.getProperty("support.email"));
		System.out.println("To " + user.getEmail());
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

}
