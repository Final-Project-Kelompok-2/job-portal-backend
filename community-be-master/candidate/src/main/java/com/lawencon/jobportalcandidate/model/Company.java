package com.lawencon.jobportalcandidate.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_company")
public class Company extends BaseEntity{
	
	@Column(name ="company_code", length = 5, nullable = false)
	private String companyCode;
	
	@Column(name = "company_name",length = 30, nullable = false)
	private String companyName;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "company_url")
	private String companyUrl;
	
	@Column(name = "company_phone",length = 15, nullable = false)
	private String companyPhone;
	
	@OneToOne
	@JoinColumn(name = "photo_id")
	private File photo;


}
