package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_candidate_references")
public class CandidateReferences {
	
	@Column(name = "fullname",length = 50, nullable=false)
	private String fullName;
	
	@Column(name = "relationship", length = 10 , nullable = false)
	private String relationship;
	
	@Column(name = "occupation",length = 20, nullable = false)
	private String occupation;
	
	@Column(name = "email", length=50, nullable =false)
	private String email;
	
	@Column (name= "company", length = 50, nullable = false)
	private String company;
	
	@Column(name="description",nullable =false)
	private String description;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
}
