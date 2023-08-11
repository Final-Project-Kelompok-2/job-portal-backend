package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateLanguageDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageResDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateLanguage;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateLanguageService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateLanguageDao candidateLanguageDao;

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private PrincipalService<String> principalService;
	
	public List<CandidateLanguageResDto> getCandidateLanguageByCandidate(String id) {
		final List<CandidateLanguage> candidateLanguage = candidateLanguageDao.getLanguageByCandidate(id);
		final List<CandidateLanguageResDto> candidateLanguageResList = new ArrayList<>();
		for (int i = 0; i < candidateLanguage.size(); i++) {
			final CandidateLanguageResDto language = new CandidateLanguageResDto();
			language.setLanguageName(candidateLanguage.get(i).getLanguageName());
			language.setId(candidateLanguage.get(i).getId());
			language.setListeningRate(candidateLanguage.get(i).getListeningRate());
			language.setSpeakingRate(candidateLanguage.get(i).getSpeakingRate());
			language.setWritingRate(candidateLanguage.get(i).getWritingRate());
			
			candidateLanguageResList.add(language);
		}
		return candidateLanguageResList;
	}
	
	public InsertResDto insertCandidateLanguage(CandidateLanguageInsertReqDto data) {
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateLanguage candidateLanguage = new CandidateLanguage();
			candidateLanguage.setLanguageName(data.getLanguageName());
			candidateLanguage.setListeningRate(data.getLanguageName());
			candidateLanguage.setSpeakingRate(data.getSpeakingRate());
			candidateLanguage.setWritingRate(data.getWritingRate());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			candidateLanguage.setCandidateUser(candidateUser);
			candidateLanguage.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateLanguage languageId = candidateLanguageDao.save(candidateLanguage);
			insertRes.setId(languageId.getId());
			insertRes.setMessage("Insert Candidate Language Success");
			em().getTransaction().commit();
		}catch(Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return insertRes;
	}
	public UpdateResDto updateCandidateLanguage(CandidateLanguageUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		try {
			em().getTransaction().begin();
			final CandidateLanguage candidateLanguage = candidateLanguageDao.getById(CandidateLanguage.class, data.getId());
			candidateLanguage.setLanguageName(data.getLanguageName());
			candidateLanguage.setListeningRate(data.getLanguageName());
			candidateLanguage.setSpeakingRate(data.getSpeakingRate());
			candidateLanguage.setWritingRate(data.getWritingRate());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			candidateLanguage.setCandidateUser(candidateUser);
			candidateLanguage.setUpdatedBy(principalService.getAuthPrincipal());
			final CandidateLanguage languageId = candidateLanguageDao.saveAndFlush(candidateLanguage);
			
			updateResDto.setMessage("Update Candidate Language Success");
			updateResDto.setVersion(languageId.getVersion());
			em().getTransaction().commit();
		}catch(Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return updateResDto;
	}
	
	public DeleteResDto DeleteCandidateLanguage(String id) {
		candidateLanguageDao.deleteById(CandidateLanguage.class, id);
		
		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Language Success");
		return deleteRes;
	}
	
	
}
