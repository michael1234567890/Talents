package com.phincon.talents.utils;

public class EmailNotification extends Notification {

	public EmailNotification(String receiver) {
		super(receiver);
	}
	
	@Override
	public String status() {
		return super.status() + "-email";
	}

}
