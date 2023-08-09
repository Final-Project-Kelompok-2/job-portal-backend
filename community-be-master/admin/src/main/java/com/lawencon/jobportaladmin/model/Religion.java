package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name ="t_religion")
public class Religion extends BaseEntity{

	@Column(name ="religion_code",  nullable = false)
	private String religionCode;
	
	@Column(name ="religion_name",  nullable = false)
	private String religionName;	
	
}
