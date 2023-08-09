package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_employment_type")
public class EmploymentType extends BaseEntity{

	@Column(name = "employment_type_code",length = 5, nullable = false)
	private String employmentTypeCode;
	
	@Column(name = "employment_type_name",length = 20, nullable = false)
	private String employmentTypeName;
	
}
