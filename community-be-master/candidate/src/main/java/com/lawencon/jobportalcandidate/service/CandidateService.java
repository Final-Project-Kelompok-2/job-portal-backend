package com.lawencon.jobportalcandidate.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateAddressDao;
import com.lawencon.jobportalcandidate.dao.CandidateDocumentsDao;
import com.lawencon.jobportalcandidate.dao.CandidateEducationDao;
import com.lawencon.jobportalcandidate.dao.CandidateFamilyDao;
import com.lawencon.jobportalcandidate.dao.CandidateLanguageDao;
import com.lawencon.jobportalcandidate.dao.CandidateProfileDao;
import com.lawencon.jobportalcandidate.dao.CandidateProjectExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateReferencesDao;
import com.lawencon.jobportalcandidate.dao.CandidateSkillDao;
import com.lawencon.jobportalcandidate.dao.CandidateStatusDao;
import com.lawencon.jobportalcandidate.dao.CandidateTrainingExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.CandidateWorkExpDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.FileTypeDao;

@Service
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
	@Autowired
	private CandidateEducationDao candidateEducationDao;
	@Autowired
	private CandidateFamilyDao candidateFamilyDao;
	@Autowired
	private CandidateLanguageDao candidateLanguageDao;
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	@Autowired
	private CandidateProjectExpDao candidateProjectExpDao;
	@Autowired
	private CandidateReferencesDao candidateReferencesDao;
	@Autowired
	private CandidateSkillDao candidateSkillDao;
	@Autowired
	private CandidateStatusDao candidateStatusDao;
	@Autowired
	private CandidateTrainingExpDao candidateTrainingDao;
	@Autowired
	private CandidateWorkExpDao candidateWorkExpDao;
	@Autowired
	private FileTypeDao fileTypeDao;
	@Autowired
	private FileDao fileDao;
	
	

	
	
	
	
	

}
