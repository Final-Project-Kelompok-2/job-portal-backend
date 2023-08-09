package com.lawencon.jobportalcandidate.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

@Entity
@Table(name = "t_saved_job")
public class SavedJob {

	@OneToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;



}
