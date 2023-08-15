package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_candidate_documents",uniqueConstraints = {@UniqueConstraint(name="userAndfileType",columnNames= {"user_id","file_type_id"})})

public class CandidateDocuments extends BaseEntity {

	@Column(name = "doc_name", length = 30, nullable = false)
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

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public CandidateUser getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(CandidateUser candidateUser) {
		this.candidateUser = candidateUser;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

}
