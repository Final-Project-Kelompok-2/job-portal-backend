package com.lawencon.jobportalcandidate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_candidate_family")
public class CandidateFamily {

	@Column(name = "fullname", length = 50, nullable = false)
	private String fullname;
	
	@Column(name = "relationship", length = 10, nullable = false)
	private String relationship;
	
	@Column(name = "degree_name", length = 50, nullable = false)
	private String degreeName;
	
	@Column(name = "occupation", length = 50, nullable = false)
	private String occupation;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "birthPlace", length = 20, nullable = false)
	private String birthPlace;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public CandidateUser getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(CandidateUser candidateUser) {
		this.candidateUser = candidateUser;
	}

}
