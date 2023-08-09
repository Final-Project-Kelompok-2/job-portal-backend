package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_hiring_status")
public class HiringStatus {

	
	@Column(name = "status_code",length = 5, nullable = false)

	private String statusCode;
	
	@Column(name = "status_name",length = 20, nullable = false)
	private String statusName;



}

