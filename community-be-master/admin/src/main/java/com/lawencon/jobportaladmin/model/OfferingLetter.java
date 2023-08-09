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
	@JoinColumn(name = "owned_benefit_id")
	private OwnedBenefit ownedBenefit;

	@Column(name = "address", length = 50, nullable = false)
	private String address;

	@Column(name = "salary", nullable = false)
	private Integer salary;
}
