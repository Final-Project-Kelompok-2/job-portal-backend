package com.lawencon.jobportalcandidate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_education")
public class CandidateEducation extends BaseEntity {

	@Column(name = "degree_name", length = 50, nullable = false)
	private String degreeName;
	
	@Column(name = "institution_name", length = 50, nullable = false)
	private String institutionName;
	
	@Column(name = "majors", length = 50, nullable = false)
	private String majors;
	
	@Column(name = "cgpa", nullable = false)
	private Float cgpa;
	
	@Column(name = "start_year", nullable = false)
	private LocalDateTime startYear;
	
	@Column(name = "end_year", nullable = false)
	private LocalDateTime endYear;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;

}
