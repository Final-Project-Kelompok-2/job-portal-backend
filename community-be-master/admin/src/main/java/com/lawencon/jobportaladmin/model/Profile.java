package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_profile")
public class Profile {

	@Column(name ="full_name",  nullable = false)
	private String fullName;
	
	@OneToOne
	@JoinColumn(name ="photo_id")
	private File photo;
	
	@Column(name ="phone_number",  nullable = true)
	private String phoneNumber;
	
	@Column(name ="address",  nullable = true)
	private String address;
	
	@OneToOne
	@JoinColumn(name ="person_type_id")
	private PersonType personType;
	
	
}
