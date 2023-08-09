package com.lawencon.jobportalcandidate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDateTime parseStringToDate(String date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		final LocalDateTime dateInput = LocalDateTime.parse(date,DateTimeFormatter.ISO_DATE_TIME);
		return dateInput;
	}
	
}
