package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="t_role")
public class Role {

	@Column(name ="role_code",  nullable = false)
	private String role_code;
	
	@Column(name ="role_name",  nullable = false)
	private String role_name;
	
}
