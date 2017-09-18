package com.phincon.talents.app.services;

import org.springframework.stereotype.Service;

import com.phincon.talents.app.model.SendingEmail;
import com.phincon.talents.app.model.SendingEmailHistory;

@Service
public class SendingEmailHistoryService {
	
	public SendingEmailHistory convertFromSendingEmail(SendingEmail sendingEmail){
		SendingEmailHistory sendingEmailHistory = new SendingEmailHistory();
		sendingEmailHistory.setCodeTemplate(sendingEmail.getCodeTemplate());
		sendingEmailHistory.setCompany(sendingEmail.getCompany());
		sendingEmailHistory.setDataContent(sendingEmail.getDataContent());
		sendingEmailHistory.setDate(sendingEmail.getDate());
		sendingEmailHistory.setEmailTo(sendingEmail.getEmailTo());
		sendingEmailHistory.setIdRef(sendingEmail.getId());
		sendingEmailHistory.setListEmpId(sendingEmail.getListEmpId());
		return sendingEmailHistory;
	}
	
	
}
