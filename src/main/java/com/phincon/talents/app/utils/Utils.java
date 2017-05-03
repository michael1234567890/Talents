package com.phincon.talents.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String INPUT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String INPUT_DATE_FORMAT = "yyyy-MM-dd";
	public static String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static String CONFIG_FILE = "/config.properties";
	public static int TIMEBETWEENPROCESS = 100;

	public static void sendEmail() {

	}

	public static Date convertStringToDate(String strDate) {
		DateFormat df = new SimpleDateFormat(INPUT_DATE_FORMAT);
		Date oldDate = null;
		try {
			oldDate = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return oldDate;
	}
	
}
