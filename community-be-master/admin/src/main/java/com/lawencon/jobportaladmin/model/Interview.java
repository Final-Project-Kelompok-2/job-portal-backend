package com.lawencon.jobportaladmin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_interview")
public class Interview {

	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	@Column(name = "interview_location", length = 50, nullable = false)
	private String interviewLocation;

	@Column(name = "interview_date", nullable = false)
	private LocalDateTime interviewDate;
}