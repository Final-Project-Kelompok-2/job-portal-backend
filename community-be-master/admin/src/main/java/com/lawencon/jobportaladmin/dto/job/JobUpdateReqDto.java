package com.lawencon.jobportaladmin.dto.job;

public class JobUpdateReqDto {

	private String id;
	private String jobName;
	private String companyId;
	private String startDate;
	private String endDate;
	private String description;
	private String expectedSalaryMin;
	private String expectedSalaryMax;
	private String employmentTypeId;
	private String fileId;
	private String file;
	private String fileExtension;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpectedSalaryMin() {
		return expectedSalaryMin;
	}

	public void setExpectedSalaryMin(String expectedSalaryMin) {
		this.expectedSalaryMin = expectedSalaryMin;
	}

	public String getExpectedSalaryMax() {
		return expectedSalaryMax;
	}

	public void setExpectedSalaryMax(String expectedSalaryMax) {
		this.expectedSalaryMax = expectedSalaryMax;
	}

	public String getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(String employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
