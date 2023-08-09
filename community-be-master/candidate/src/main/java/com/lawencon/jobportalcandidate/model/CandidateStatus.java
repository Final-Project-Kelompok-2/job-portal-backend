package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table (name = "t_candidate_status")
public class CandidateStatus extends BaseEntity {

	@Column(name = "status_code", length = 5, nullable = false)
	private String statusCode;
	
	@Column(name = "status_name", length = 20, nullable = false)
	private String statusName;
}
