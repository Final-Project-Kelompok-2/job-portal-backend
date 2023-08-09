package com.lawencon.jobportaladmin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_review_detail")
public class ReviewDetail {

	@OneToOne
	@JoinColumn(name ="review_id")
	private Review review;
	
	@OneToOne
	@JoinColumn(name ="question_id")
	private Question question;
	
}
