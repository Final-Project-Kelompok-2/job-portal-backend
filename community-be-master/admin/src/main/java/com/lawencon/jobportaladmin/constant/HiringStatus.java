package com.lawencon.jobportaladmin.constant;

public enum HiringStatus {

	APPLIED("S-001", "APPLIED"),ASSESMENT("S-002", "APPLIED"),
	INTERVIEWUSER("S-003", "APPLIED"),MCU("S-004", "APPLIED"),
	OFFERING("S-005", "APPLIED"),HIRED("S-006", "APPLIED"),
	REJECT("S-007", "APPLIED");
	
	public final String statusCode;
	public final String statusName;

	HiringStatus(String statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}
}
