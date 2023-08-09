package com.lawencon.jobportaladmin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_candidate_project_exp")
public class CandidateProjectExp {
	@Column(name = "project_name", length=30 , nullable=false)
	private String projectName;
	
	@Column(name = "project_url" , nullable=true)
	private String projectUrl;
	
	@Column(name = "description", nullable=false)
	private String description;
	
	@Column(name = "start_date" , nullable=false)
	private LocalDateTime startDate;
	
	
	@Column(name ="end_date",nullable=false)
	private LocalDateTime endDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
}