package com.lawencon.jobportaladmin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_assesment")
public class Assesment extends BaseEntity{

	@Column(name ="assesment_date", nullable = false)
	private LocalDateTime assesmentDate;
	
	
	@Column(name ="assesment_date", length = 50, nullable = false)
	private String assesmentLocation;
	
	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
}
