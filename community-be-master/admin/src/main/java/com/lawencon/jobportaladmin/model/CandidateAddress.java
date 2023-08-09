package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_address")
public class CandidateAddress extends BaseEntity{

	@Column(name = "address", nullable= false)
	private String address;
	
	@Column(name = "residence_type", length=10, nullable= false)
	private String residenceType;
	
	@Column(name= "country", length=20, nullable= false)
	private String country;
	
	@Column(name= "province", length=20, nullable= false)
	private String province;
	
	@Column(name= "city", length=20, nullable= false)
	private String city;
	
	@Column(name= "postal_code", length=20, nullable= false)
	private String postalCode;
	
	@OneToOne
	@JoinColumn(name= "user_id")
	private CandidateUser candidateUser;
}
