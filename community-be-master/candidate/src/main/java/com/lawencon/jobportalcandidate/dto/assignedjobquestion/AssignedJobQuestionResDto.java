package com.lawencon.jobportalcandidate.dto.assignedjobquestion;

public class AssignedJobQuestionResDto {
	private String id;
	private String jobId;
	private String questionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
}
