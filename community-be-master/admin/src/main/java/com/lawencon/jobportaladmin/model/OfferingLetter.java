package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_offering_letter")
public class OfferingLetter {
	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	@OneToOne
	@JoinColumn(name = "benefit_id")
	private Benefit benefit;

	@Column(name = "location", length = 50, nullable = false)
	private String location;

	@Column(name = "salary", nullable = false)
	private Integer salary;
}