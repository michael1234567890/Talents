package com.phincon.talents.app.scheduling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.SendingEmailHistoryRepository;
import com.phincon.talents.app.dao.SendingEmailRepository;
import com.phincon.talents.app.model.SendingEmail;
import com.phincon.talents.app.model.SendingEmailHistory;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.services.SendingEmailHistoryService;

@Component
public class SendingEmailScheduling {
private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	@Autowired
	SendingEmailRepository sendingEmailRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	SendingEmailHistoryRepository sendingEmailHistoryRepository;
	
	@Autowired
	SendingEmailHistoryService sendingEmailHistoryService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messages;
	
	@Scheduled(fixedRate = 60000) // milliseconds between each method start
	public void sendMail(){
		PageRequest pageable = new PageRequest(0, 20);
		Page<SendingEmail> listSendingEmail = sendingEmailRepository.findAll(pageable);
		for (SendingEmail sendingEmail : listSendingEmail) {
			String emailSending = sendingEmail.getEmailTo();
			if(emailSending == null || emailSending.equals("")) {
				String strEmpId = sendingEmail.getListEmpId();
				List<String> listStrEmpId =new ArrayList<String>();
				if(strEmpId != null && strEmpId.contains(",")) {
					String[] strEmpArray = strEmpId.split(",");
					for (String strEmp : strEmpArray) {
						listStrEmpId.add(strEmp);
					}
				}else {
					listStrEmpId.add(strEmpId);
				}
				
				
				for (String strId : listStrEmpId) {
					// get employee obj
					Long empId = Long.valueOf(strId);
					Employee employee = employeeRepository.findOne(empId);
					String receiver = employee.getName();
					String email = employee.getOfficeMail();
					String content = sendingEmail.getDataContent();
					content = content.replace("{receiver}", receiver);
					String subject = sendingEmail.getSubject();
					mailSender.send(constructEmail(subject,content,email));
				}
				
				// insert sending mail object to history
				
				SendingEmailHistory sendingEmailHistory = sendingEmailHistoryService.convertFromSendingEmail(sendingEmail) ;// SendingEmailHistory.class.cast(sendingEmail) ;
				sendingEmailHistoryRepository.save(sendingEmailHistory);
				// delete sending mail object
				sendingEmailRepository.delete(sendingEmail);
				
				
				
			}
		}
		
		System.out.println(
				"Sending Mail Job ran at " +dateFormat.format(new Date()));
	}
	
	

	private SimpleMailMessage constructEmail(String subject, String body,
			String emailTo) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(emailTo);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
}
