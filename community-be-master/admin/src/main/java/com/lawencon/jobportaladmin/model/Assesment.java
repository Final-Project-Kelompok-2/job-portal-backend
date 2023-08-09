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
public class Assesment extends BaseEntity {

	@Column(name = "assesment_date", nullable = false)
	private LocalDateTime assesmentDate;

	@Column(name = "assesment_location", length = 50, nullable = false)
	private String assesmentLocation;

	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	public LocalDateTime getAssesmentDate() {
		return assesmentDate;
	}

	public void setAssesmentDate(LocalDateTime assesmentDate) {
		this.assesmentDate = assesmentDate;
	}

	public String getAssesmentLocation() {
		return assesmentLocation;
	}

	public void setAssesmentLocation(String assesmentLocation) {
		this.assesmentLocation = assesmentLocation;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

}
