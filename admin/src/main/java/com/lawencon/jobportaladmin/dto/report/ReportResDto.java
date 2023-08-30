package com.lawencon.jobportaladmin.dto.report;

import java.time.LocalDateTime;

public class ReportResDto {

	private String fullName;
	private String jobName;
	private LocalDateTime timeDifference;
	private String employmentTypeName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public LocalDateTime getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(LocalDateTime timeDifference) {
		this.timeDifference = timeDifference;
	}

	public String getEmploymentTypeName() {
		return employmentTypeName;
	}

	public void setEmploymentTypeName(String employmentTypeName) {
		this.employmentTypeName = employmentTypeName;
	}

}
