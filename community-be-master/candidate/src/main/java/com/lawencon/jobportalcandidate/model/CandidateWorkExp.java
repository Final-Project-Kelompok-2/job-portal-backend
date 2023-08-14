package com.lawencon.jobportalcandidate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_work_exp")
public class CandidateWorkExp extends BaseEntity{
	@Column(name = "position_name",length = 30, nullable = false)
	private String positionName;
	
	@Column(name = "company_name",length = 30,nullable = false)
	private String companyName;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@Column(name = "responsibility",nullable = false)
	private String responsibility;
	
	@Column(name = "reason_leave",nullable = false)
	private String reasonLeave;
	
	@Column(name = "last_salary", nullable = false)
	private Float lastSalary;
	
	@Column(name = "start_date",nullable = false)
	private LocalDateTime startDate;
	
	@Column(name= "end_date",nullable = false)
	private LocalDateTime endDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public String getReasonLeave() {
		return reasonLeave;
	}

	public void setReasonLeave(String reasonLeave) {
		this.reasonLeave = reasonLeave;
	}

	public Float getLastSalary() {
		return lastSalary;
	}

	public void setLastSalary(Float lastSalary) {
		this.lastSalary = lastSalary;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public CandidateUser getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(CandidateUser candidateUser) {
		this.candidateUser = candidateUser;
	}
	
}
