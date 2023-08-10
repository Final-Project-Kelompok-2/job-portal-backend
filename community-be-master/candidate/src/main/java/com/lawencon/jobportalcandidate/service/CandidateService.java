package com.lawencon.jobportalcandidate.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateAddressDao;
import com.lawencon.jobportalcandidate.dao.CandidateDocumentsDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;

public class CandidateService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateAddressDao candidateAddressDao;
	@Autowired
	private CandidateDocumentsDao candidateDocumentDao;

}
