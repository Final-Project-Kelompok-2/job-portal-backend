package com.lawencon.jobportaladmin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_applicant")
public class Applicant extends BaseEntity{

	@Column(name = "applicant_code",length = 5, nullable = false)
	private String applicantCode;
			
	@Column(name = "applied_date", nullable = false)
	private LocalDateTime appliedDate;
	
	@OneToOne
	@JoinColumn(name = "job_id")
	private Job job;	
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private HiringStatus status;
	
	@OneToOne
	@JoinColumn(name = "candidate_id")
	private CandidateUser candidate;
	
	
	
}
