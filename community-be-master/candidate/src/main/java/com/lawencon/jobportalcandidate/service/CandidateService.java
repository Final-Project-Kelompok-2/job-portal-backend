package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterResDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateAddress;
import com.lawencon.jobportalcandidate.model.CandidateDocuments;
import com.lawencon.jobportalcandidate.model.CandidateEducation;
import com.lawencon.jobportalcandidate.model.CandidateFamily;
import com.lawencon.jobportalcandidate.model.CandidateLanguage;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateProjectExp;
import com.lawencon.jobportalcandidate.model.CandidateReferences;
import com.lawencon.jobportalcandidate.model.CandidateSkill;
import com.lawencon.jobportalcandidate.model.CandidateStatus;
import com.lawencon.jobportalcandidate.model.CandidateTrainingExp;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.CandidateWorkExp;

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

	
	public List<CandidateMasterResDto> getCandidateDataByCandidate(String id){
		final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, id);
		final CandidateAddress candidateAddress = candidateAddressDao.getByCandidateId(id);
		final CandidateProfile candidateProfile = candidateProfileDao.getById(CandidateProfile.class, candidateUser.getCandidateProfile().getId());
		final List<CandidateDocuments> candidateDocuments = candidateDocumentDao.getCandidateDocumentsByCandidate(id);
		final List<CandidateEducation> candidateEducations = candidateEducationDao.getEducationByCandidate(id);
		final List<CandidateFamily> candidateFamilies = candidateFamilyDao.getFamilyByCandidate(id);
		final List<CandidateLanguage> candidateLanguage = candidateLanguageDao.getLanguageByCandidate(id);
		final List<CandidateProjectExp> candidateProjectExp = candidateProjectExpDao.getByCandidate(id);
		final List<CandidateReferences>candidateReferences = candidateReferencesDao.getByCandidate(id);
		final List<CandidateSkill> candidateSkill = candidateSkillDao.getByCandidate(id);
		final CandidateStatus candidateStatus = candidateStatusDao.getById(CandidateStatus.class, candidateProfile.getCandidateStatus().getId());
		final List<CandidateTrainingExp> candidateTrainingExp = candidateTrainingDao.getByCandidate(id);
		final List<CandidateWorkExp> candidateWorkExp = candidateWorkExpDao.getByCandidate(id);
		
		final List<CandidateMasterResDto> candidateMasterResList = new ArrayList<>();
		
		return candidateMasterResList;
		
	}
	
	
	public InsertResDto InsertCandidate(CandidateMasterInsertReqDto data) {
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return insertRes;
	}

	public UpdateResDto updateCandidate(CandidateMasterUpdateReqDto data) {
		final UpdateResDto updateRes = new UpdateResDto();
		try {
			em().getTransaction().begin();
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return updateRes;
	}
	
	

}
