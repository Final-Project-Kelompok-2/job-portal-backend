package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_candidate_user")
public class CandidateUser {
	@Column(name = "user_email",length = 50, nullable = false)
	private String userEmail;
	
	@Column(name = "user_password",nullable = false)
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name = "t_candidate_profile")
	private CandidateProfile candidateProfile;
	
}
