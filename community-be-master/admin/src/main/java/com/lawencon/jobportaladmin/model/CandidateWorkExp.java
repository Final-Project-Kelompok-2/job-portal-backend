package com.lawencon.jobportaladmin.model;

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
	
	
	
}
