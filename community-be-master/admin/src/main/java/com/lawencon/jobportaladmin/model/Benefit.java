package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_benefit")
public class Benefit extends BaseEntity {

	@Column(name ="benefit_code", length = 5, nullable = false)
	private String benefitCode;
	
	@Column(name = "benefit_name",length = 20, nullable = false)
	private String benefitName;
}
