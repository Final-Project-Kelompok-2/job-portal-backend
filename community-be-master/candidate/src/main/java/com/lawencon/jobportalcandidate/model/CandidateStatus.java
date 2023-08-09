package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "t_candidate_status")
public class CandidateStatus {

	@Column(name = "status_code", length = 5, nullable = false)
	private String statusCode;
	
	@Column(name = "status_name", length = 20, nullable = false)
	private String statusName;
	
}
