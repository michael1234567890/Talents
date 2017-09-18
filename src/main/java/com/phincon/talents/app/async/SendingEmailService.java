package com.phincon.talents.app.async;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.phincon.talents.app.dao.SendingEmailRepository;
import com.phincon.talents.app.model.SendingEmail;

@Component
@Service
public class SendingEmailService {
	@Autowired
	SendingEmailRepository sendingEmailRepository;

	@Async
	public Future<Boolean> sendMail(SendingEmail objSendingEmail)
			throws InterruptedException {
		sendingEmailRepository.save(objSendingEmail);
		return new AsyncResult<Boolean>(true);
	}
}
