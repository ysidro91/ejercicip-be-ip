package com.exercise.ya.util;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
		return sdf.format(date);
	}
	
	public static String changeTimezone(Date date, String timezone) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("hh:mm:ss")
                .withZone(ZoneId.of(timezone));
		return dateFormat.format(date.toInstant());
	}

}
