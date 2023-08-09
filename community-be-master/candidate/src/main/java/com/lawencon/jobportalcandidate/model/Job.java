	package com.lawencon.jobportalcandidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_job")
public class Job {

	@Column(name = "job_code",length = 5, nullable = false)
	private String jobCode;
	
	@Column(name = "job_name",length = 30, nullable = false)
	private String jobName;
	
	@OneToOne
	@JoinColumn(name = "company_id")
	private Company Company;
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;
		
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "expected_salary_min", nullable = true)
	private Integer expectedSalaryMin;	
	
	@Column(name = "expected_salary_max", nullable = true)
	private Integer expectedSalaryMax;
	
	@OneToOne
	@JoinColumn(name = "employment_type_id")
	private EmploymentType employmentType;
		
	@OneToOne
	@JoinColumn(name = "job_picture_id")
	private File jobPicture;

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public File getJobPicture() {
		return jobPicture;
	}

	public void setJobPicture(File jobPicture) {
		this.jobPicture = jobPicture;
	}	
}
