package com.lawencon.jobportaladmin.dto.candidateskill;

public class CandidateSkillInsertReqDto {
	private String skillName;
	private String candidateId;

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

}
