package com.lawencon.jobportalcandidate.dto.job;

public class JobInsertReqDto {

	private String jobName;
	private String jobCode;
	private String companyId;
	private String startDate;
	private String endDate;
	private String description;
	private Integer expectedSalaryMin;
	private Integer expectedSalaryMax;
	private String employmentTypeId;
	private String file;
	private String fileExtension;

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

	public String getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(String employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
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

	public Integer getExpectedSalaryMin() {
		return expectedSalaryMin;
	}

	public void setExpectedSalaryMin(Integer expectedSalaryMin) {
		this.expectedSalaryMin = expectedSalaryMin;
	}

	public Integer getExpectedSalaryMax() {
		return expectedSalaryMax;
	}

	public void setExpectedSalaryMax(Integer expectedSalaryMax) {
		this.expectedSalaryMax = expectedSalaryMax;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

}
