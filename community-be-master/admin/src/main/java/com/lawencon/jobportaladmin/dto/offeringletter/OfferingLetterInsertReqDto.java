package com.lawencon.jobportaladmin.dto.offeringletter;

public class OfferingLetterInsertReqDto {

	private String applicantId;
	private String address;
	private Integer salary;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

}
