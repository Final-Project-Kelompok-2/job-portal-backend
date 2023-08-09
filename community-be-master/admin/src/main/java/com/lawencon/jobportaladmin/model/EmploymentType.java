package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_employment_type")
public class EmploymentType {

	@Column(name = "employment_type_code",length = 5, nullable = false)
	private String employmentTypeCode;
	
	@Column(name = "employment_type_name",length = 20, nullable = false)
	private String employmentTypeName;
	
}
