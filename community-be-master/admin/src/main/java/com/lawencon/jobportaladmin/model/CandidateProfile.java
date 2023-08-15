package com.lawencon.jobportaladmin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_profile",uniqueConstraints = {@UniqueConstraint(name="nikAndPhoneNumber",columnNames= {"nik","phone_number"})})
public class CandidateProfile extends BaseEntity {

	@Column(name = "salutation", length = 4, nullable = false)
	private String salutation;

	@Column(name = "fullname", length = 50, nullable = false)
	private String fullname;

	@Column(name = "gender", length = 10, nullable = false)
	private String gender;

	@Column(name = "experience", length = 10, nullable = false)
	private String experience;

	@Column(name = "expected_salary", nullable = false)
	private Float expectedSalary;

	@Column(name = "phone_number", length = 20, nullable = false)
	private String phoneNumber;

	@Column(name = "mobile_number", length = 20, nullable = false)
	private String mobileNumber;

	@Column(name = "nik", length = 50, nullable = false,unique = true)
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

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Float getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(Float expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public CandidateStatus getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

}
