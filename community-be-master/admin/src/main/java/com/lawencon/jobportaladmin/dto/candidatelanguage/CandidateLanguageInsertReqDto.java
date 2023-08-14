package com.lawencon.jobportaladmin.dto.candidatelanguage;

public class CandidateLanguageInsertReqDto {
	private String languageName;
	private String writingRate;
	private String speakingRate;
	private String listeningRate;
	private String email;

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getWritingRate() {
		return writingRate;
	}

	public void setWritingRate(String writingRate) {
		this.writingRate = writingRate;
	}

	public String getSpeakingRate() {
		return speakingRate;
	}

	public void setSpeakingRate(String speakingRate) {
		this.speakingRate = speakingRate;
	}

	public String getListeningRate() {
		return listeningRate;
	}

	public void setListeningRate(String listeningRate) {
		this.listeningRate = listeningRate;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
