package com.lawencon.jobportalcandidate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_candidate_education")
public class CandidateEducation {

	@Column(name = "degree_name", length = 50, nullable = false)
	private String degreeName;
	
	@Column(name = "institution_name", length = 50, nullable = false)
	private String institutionName;
	
	@Column(name = "majors", length = 50, nullable = false)
	private String majors;
	
	@Column(name = "cgpa", nullable = false)
	private Float cgpa;
	
	@Column(name = "start_year", nullable = false)
	private LocalDateTime startYear;
	
	@Column(name = "end_year", nullable = false)
	private LocalDateTime endYear;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public CandidateUser getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(CandidateUser candidateUser) {
		this.candidateUser = candidateUser;
	}

	public Float getCgpa() {
		return cgpa;
	}

	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}

	public LocalDateTime getStartYear() {
		return startYear;
	}

	public void setStartYear(LocalDateTime startYear) {
		this.startYear = startYear;
	}

	public LocalDateTime getEndYear() {
		return endYear;
	}

	public void setEndYear(LocalDateTime endYear) {
		this.endYear = endYear;
	}

}
