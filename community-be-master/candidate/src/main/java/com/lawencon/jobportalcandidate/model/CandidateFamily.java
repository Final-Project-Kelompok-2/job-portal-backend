package com.lawencon.jobportalcandidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_family")
public class CandidateFamily extends BaseEntity {

	@Column(name = "fullname", length = 50, nullable = false)
	private String fullname;
	
	@Column(name = "relationship", length = 10, nullable = false)
	private String relationship;
	
	@Column(name = "degree_name", length = 50, nullable = false)
	private String degreeName;
	
	@Column(name = "occupation", length = 50, nullable = false)
	private String occupation;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "birthPlace", length = 20, nullable = false)
	private String birthPlace;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
}
