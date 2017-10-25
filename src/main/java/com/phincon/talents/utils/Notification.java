package com.phincon.talents.utils;

public class Notification {
	private String receiver;
	protected String isRead = "false";

	public Notification(String receiver) {
		this.receiver = receiver;
	}

	public boolean validReceiver() {

		if (this.receiver.isEmpty() || this.receiver == null) {
			return false;
		} else {
			return true;
		}
	}

	public void markAsRead(String isRead) {
		if (!isRead.equals("false")) {
			this.isRead = "true";
		} else {
			this.isRead = "false";
		}
	}

	public String status() {
		if (this.isRead.equals("true"))
			return "read";
		else
			return "unread";
	}

}
