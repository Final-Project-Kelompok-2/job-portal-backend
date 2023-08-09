package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_blacklist")
public class Blacklist extends BaseEntity{
	@Column(name ="email", length = 50, nullable = false)
	private String email;
	
	@Column(name ="notes",  nullable = false)
	private String notes;	
}
