package com.lawencon.jobportaladmin.dto.employee;

public class EmployeeInsertReqDto {
	private String jobId;
	private String candidateId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

}
