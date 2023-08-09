package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_question_option")
public class QuestionOption {


	@Column(name ="option_label",  nullable = false)
	private String optionLabel;
	
	@Column(name ="is_correct",  nullable = false)
	private Boolean isCorrect;
	
	@OneToOne
	@JoinColumn(name ="question_id")
	private Question question;
	
}