package com.lawencon.jobportaladmin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_profile")
public class CandidateProfile extends BaseEntity{

	@Column(name = "salutation",length = 4, nullable = false)
	private String salutation;
	
	@Column(name = "fullname",length = 50, nullable = false)
	private String fullname;

	@Column(name = "gender",length = 10,nullable = false)
	private String gender;
	
	@Column(name = "experience",length = 10, nullable = false)
	private String experience;
	
	@Column(name = "expected_salary",nullable = false)
	private Float expectedSalary;
	
	@Column(name = "phone_number",length = 20, nullable =false)
	private String phoneNumber;
	
	@Column(name = "mobile_number",length = 20 , nullable = false)
	private String mobileNumber;
	
	@Column(name = "nik", length = 50, nullable = false)
	private String nik;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "birth_place", length = 20, nullable = false)
	private String birthPlace;
	
	@OneToOne
	@JoinColumn(name = "marital_status_id")
	private MaritalStatus maritalStatus;
	
	@OneToOne
	@JoinColumn(name = "religion_id")
	private Religion religion;
	
	@OneToOne
	@JoinColumn(name = "person_type_id")
	private PersonType personType;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "candidate_status_id")
	private CandidateStatus candidateStatus;

}
