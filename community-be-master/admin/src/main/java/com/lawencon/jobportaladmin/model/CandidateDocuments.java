package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_documents")
public class CandidateDocuments extends BaseEntity{

	@Column(name = "doc_name", length= 30, nullable= false)
	private String docName;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private CandidateUser candidateUser;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "file_type_id")
	private FileType fileType;

}
