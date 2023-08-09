package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name ="t_role")
public class Role extends BaseEntity{

	@Column(name ="role_code", length = 5, nullable = false)
	private String role_code;
	
	@Column(name ="role_name",  length = 10,nullable = false)
	private String role_name;
	
}
