package com.lawencon.jobportaladmin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_question_answer")
public class QuestionAnswer {

	@OneToOne
	@JoinColumn(name ="option_id")
	private QuestionOption questionOption;
	
	@OneToOne
	@JoinColumn(name ="candidate_id")
	private CandidateUser candidateUser;
	
	@OneToOne
	@JoinColumn(name ="question_id")
	private Question question;
}
