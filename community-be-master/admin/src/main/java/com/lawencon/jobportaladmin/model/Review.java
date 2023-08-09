package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_review")
public class Review {

	@Column(name ="notes",  nullable = false)
	private String notes;
	
	@Column(name ="score",  nullable = false)
	private Float score;
	
	@OneToOne
	@JoinColumn(name ="applicant_id")
	private Applicant applicant;
	
}