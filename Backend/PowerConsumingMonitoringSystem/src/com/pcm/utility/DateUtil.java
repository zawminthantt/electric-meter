package com.pcm.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	
	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date getCurrentDateWithoutMilli(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date convertFromString(String string, DateFormat format) {
		if (string == null || string.trim().length() == 0) {
			return null;
		}

		try {
			return format.parse(string);
		} catch (ParseException e) {
			System.out.println("Unable to convert given object to date: " + string);
			return null;
		}
	}
	
	public static String convertFromDate(Date date, DateFormat format) {
		if (date == null) {
			return "";
		}

		return format.format(date);
	}
}
