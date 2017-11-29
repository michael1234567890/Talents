package com.phincon.talents.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*!@$]).{8,20})";
	//private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

	public PasswordValidator(String formatPattern) {
		this.pattern = Pattern.compile(PASSWORD_PATTERN);
		//this.pattern = Pattern.compile(formatPattern);
	}

	/**
	 * Validate password with regular expression
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean validate(final String password) {
		this.matcher = this.pattern.matcher(password);
		boolean ismatch = this.matcher.matches();
		return ismatch;

	}
}
