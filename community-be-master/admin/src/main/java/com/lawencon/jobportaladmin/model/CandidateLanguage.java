package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_language")
public class CandidateLanguage extends BaseEntity{

	@Column(name = "language_name", length = 30, nullable = false)
	private String languageName;
	
	@Column(name = "writing_rate", length = 2, nullable = false)
	private String writingRate;
	
	@Column(name = "speaking_rate", length = 2, nullable = false)
	private String speakingRate;
	
	@Column(name = "listening_rate", length = 2, nullable = false)
	private String listeningRate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;

}
