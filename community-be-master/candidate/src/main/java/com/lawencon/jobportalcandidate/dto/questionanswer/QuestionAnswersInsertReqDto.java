package com.lawencon.jobportalcandidate.dto.questionanswer;

import java.util.List;

public class QuestionAnswersInsertReqDto {

	private List<QuestionAnswerInsertReqDto> answers;

	public List<QuestionAnswerInsertReqDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionAnswerInsertReqDto> answers) {
		this.answers = answers;
	}
	
}
