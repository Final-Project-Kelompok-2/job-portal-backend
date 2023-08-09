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

}
