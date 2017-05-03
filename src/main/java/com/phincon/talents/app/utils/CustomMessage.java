package com.phincon.talents.app.utils;

public class CustomMessage {
	private String message;
	private boolean error = false;

	public CustomMessage() {
	}

	public CustomMessage(String message, boolean error) {
		this.message = message;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
