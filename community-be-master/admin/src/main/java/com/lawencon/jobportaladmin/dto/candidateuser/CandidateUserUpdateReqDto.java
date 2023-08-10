package com.lawencon.jobportaladmin.dto.candidateuser;

import com.lawencon.jobportaladmin.dto.candidateprofile.CandidateProfileUpdateReqDto;

public class CandidateUserUpdateReqDto {

	private String id;
	private String userEmail;
	private String userPassword;
	private CandidateProfileUpdateReqDto profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public CandidateProfileUpdateReqDto getProfile() {
		return profile;
	}

	public void setProfile(CandidateProfileUpdateReqDto profile) {
		this.profile = profile;
	}

}
