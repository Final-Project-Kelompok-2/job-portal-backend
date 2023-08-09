package com.lawencon.jobportalcandidate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_training_exp")
public class CandidateTrainingExp extends BaseEntity {
	@Column(name = "organization_name",length = 20 ,nullable =false)
	private String organizationName;
	
	@Column(name = "training_name" , length = 20, nullable = false)
	private String trainingName;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@Column(name = "start_date",nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "end_date",nullable = false)
	private LocalDateTime endDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
}
