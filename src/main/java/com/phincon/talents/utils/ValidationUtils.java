package com.phincon.talents.utils;

import java.util.regex.Pattern;

// import org.apache.commons.lang3.StringUtils;


public final class ValidationUtils {
	private ValidationUtils() throws Exception {
		throw new Exception(
				"Utility classes cannot be instantiated");
	}
	
	public static void assertNotBlank(String value, String message){
//		if(StringUtils.isBlank(value)) {
//			throw new IllegalArgumentException(message);
//		}
	}
	
	public static void assertMinimumLength(String value, int length, String message){
		if(value.length() < length) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void assertMatches(String value,Pattern regex, String message){
		if(!regex.matcher(value).matches()) {
			throw new IllegalArgumentException(message);
		}
	}
	
	
}
