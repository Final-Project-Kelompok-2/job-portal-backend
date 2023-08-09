package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name ="t_user")
public class User extends BaseEntity{

	@Column(name ="user_email", length = 50, nullable = false)
	private String userEmail;
	
	@Column(name ="user_password",  nullable = false)
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name ="profile_id")
	private Profile profile;
	
	@OneToOne
	@JoinColumn(name ="role_id")
	private Role role;
	
}
