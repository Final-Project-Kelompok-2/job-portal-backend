package com.lawencon.jobportalcandidate.dto.question;

import java.util.List;

import com.lawencon.jobportalcandidate.dto.questionoption.QuestionOptionInsertReqDto;

public class QuestionInsertReqDto {

	private String questionDetail;
	private List<QuestionOptionInsertReqDto> options;

	public String getQuestionDetail() {
		return questionDetail;
	}

	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}

	public List<QuestionOptionInsertReqDto> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOptionInsertReqDto> options) {
		this.options = options;
	}

}
