package com.lawencon.jobportalcandidate.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDateTime parseStringToLocalDateTime(String date) {
		final LocalDateTime dateInput = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		return dateInput;
	}
	
	public static LocalDate parseStringToLocalDate(String date) {
		final LocalDate dateInput = LocalDate.parse(date);
		return dateInput;
	}
	
	

}