package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="t_religion")
public class Religion {

	@Column(name ="religion_code",  nullable = false)
	private String religionCode;
	
	@Column(name ="religion_name",  nullable = false)
	private String religionName;	
	
}
