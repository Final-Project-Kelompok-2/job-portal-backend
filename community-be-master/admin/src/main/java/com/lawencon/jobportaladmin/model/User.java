package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_user")
public class User {

	@Column(name ="user_email",  nullable = false)
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
