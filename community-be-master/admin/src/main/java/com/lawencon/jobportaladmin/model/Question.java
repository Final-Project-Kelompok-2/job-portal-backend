package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="t_question")
public class Question {

	@Column(name ="question_code",  nullable = false)
	private String questionCode;
	
	
	@Column(name ="question_detail",  nullable = false)
	private String questionDetail;
}
