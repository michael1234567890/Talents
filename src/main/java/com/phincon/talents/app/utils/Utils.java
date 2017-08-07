package com.phincon.talents.app.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sun.misc.BASE64Decoder;

public class Utils {
	public static String INPUT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String INPUT_DATE_FORMAT = "yyyy-MM-dd";
	public static String INPUT_DATE_FORMAT_ID = "dd-MM-yyyy";
	public static String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static String CONFIG_FILE = "/config.properties";
	public static String UPLOAD_ROOT_PATH = "D:/upload/";
	public static String UPLOAD_IMAGE_TYPE = "jpg";
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
	
	public static String convertDateToString(Date date){
		DateFormat fmt = new SimpleDateFormat(INPUT_DATE_FORMAT_ID);
		String text = fmt.format(date);
		return text;
	}

	public static void createImage(String image, String filename) {
		String fileType = UPLOAD_IMAGE_TYPE;
		// String path = UPLOAD_ROOT_PATH;
		String path = GlobalValue.PATH_UPLOAD;
		System.out.println("Path Upload : " + path);

		try {
			BufferedImage bufImg = decodeToImage(image);
			File imgOutFile = new File(path + filename);
			ImageIO.write(bufImg, fileType, imgOutFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String convertImageToBase64(String path) {

		String pathupload = GlobalValue.PATH_UPLOAD;		
		String fullpath = pathupload + path;
		File file = new File(fullpath);
		try {
			// Reading a Image file from file system
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			String imageDataString = encodeImage(imageData);
			imageInFile.close();
			System.out.println("Image Successfully Manipulated!");
			return imageDataString;

		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return null;
	}
	
	
	

	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray
	 *            - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray) {
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/jpeg;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imageByteArray,
				false)));
		return sb.toString();
		// return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString
	 *            - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	public static Map<String, Object> convertStrJsonToMap(String strJson) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> paramsMap = null;
		try {
			paramsMap = mapper.readValue(strJson, Map.class);

		} catch (Exception e) {
			throw new RuntimeException("Error :  Problem with convert Data");
		}
		return paramsMap;
	}

	public static String encrypt(String strEncrypt) {
		String crypPassword = new BCryptPasswordEncoder().encode(strEncrypt);
		return crypPassword;

	}

	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
						.get(Calendar.DAY_OF_YEAR);
		return sameDay;
	}
	
	public static int diffMonth(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startDate);
		endCal.setTime(endDate);
		int diffYear = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
		return diffMonth;
	}

	public static Long diffDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		long milliseconds1 = cal1.getTimeInMillis();
		long milliseconds2 = cal2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}
	
	public static Date addDay(Date dt,int amount) throws ParseException{
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, amount);
		dt = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateStr = sdf.format(dt);
		dt = sdf.parse(dateStr);
		return dt;
	}

	public static boolean comparingDate(Date date1, Date date2, String operation) {
		if (operation.equals(">=")) {
			if (date1.after(date2) || date1.equals(date2))
				return true;
		} else if (operation.equals("<=")) {
			if (date1.before(date2) || date1.equals(date2))
				return true;
		} else if (operation.equals(">")) {
			if (date1.after(date2))
				return true;
		} else if (operation.equals("<")) {
			if (date1.before(date2))
				return true;
		} else {
			if (date1.equals(date2))
				return true;
		}
		return false;
	}
	
	public static String getUrlAttachment(String http, HttpServletRequest request,String path){
		String url=null;
		if(request != null){
			url = http +  request.getServerName() + ":"
					+ request.getServerPort() + "/public/getImage?path="+path;
		}
		return url;
	}

}
