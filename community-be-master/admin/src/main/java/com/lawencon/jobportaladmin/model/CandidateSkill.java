package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_skill")
public class CandidateSkill extends BaseEntity{
	
	@Column(name = "skill_name", nullable = false)
	private String skillName ;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
}
