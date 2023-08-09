package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_benefit")
public class Benefit {

	@Column(name ="benefit_code", length = 5, nullable = false)
	private String benefitCode;
	
	@Column(name = "benefit_name",length = 20, nullable = false)
	private String benefitName;
}
