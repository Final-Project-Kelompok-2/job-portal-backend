package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_religion")
public class Religion {

	@Column(name = "religion_code", length = 5, nullable = false, unique = true)
	private String religionCode;
	
	@Column(name = "religion_name", length = 20, nullable = false)
	private String religionName;


}
