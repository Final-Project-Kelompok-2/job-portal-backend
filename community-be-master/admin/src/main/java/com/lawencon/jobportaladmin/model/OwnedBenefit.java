package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_owned_benefit")
public class OwnedBenefit {

	@OneToOne
	@JoinColumn(name ="benefit_id")
	private Benefit benefit;
	
	@OneToOne
	@JoinColumn(name ="job_id")
	private Job job;
	
}
