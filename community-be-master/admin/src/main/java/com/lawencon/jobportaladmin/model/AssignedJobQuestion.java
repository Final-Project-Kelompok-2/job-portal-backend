package com.lawencon.jobportaladmin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_assigned_job_question")
public class AssignedJobQuestion extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@OneToOne
	@JoinColumn(name = "question_id")
	private Question question;
}