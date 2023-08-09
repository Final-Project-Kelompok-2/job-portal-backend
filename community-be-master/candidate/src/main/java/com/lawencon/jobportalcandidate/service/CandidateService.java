package com.lawencon.jobportalcandidate.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.jobportalcandidate.dao.CandidateAddressDao;
import com.lawencon.jobportalcandidate.dao.CandidateDocumentsDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;

public class CandidateService {
	
	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateAddressDao candidateAddressDao;
	@Autowired
	private CandidateDocumentsDao candidateDocumentDao;
	

}
