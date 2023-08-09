package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_marital_status")
public class MaritalStatus {
	@Column(name = "marital_code",length = 5 , nullable = false)
	private String maritalCode;
	
	@Column(name = "marital_name",length = 20 , nullable = false)
	private String maritalName;
}
