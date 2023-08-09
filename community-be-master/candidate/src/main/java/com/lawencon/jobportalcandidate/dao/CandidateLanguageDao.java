package com.lawencon.jobportalcandidate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateLanguage;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Repository
public class CandidateLanguageDao extends AbstractJpaDao{
	private EntityManager em = ConnHandler.getManager();
	
	public List<CandidateLanguage>getLanguageByCandidate(String id){
		final String sql = "SELECT  "
				+ "	id, "
				+ "	language_name, "
				+ "	writing_rate, "
				+ "	speaking_rate, "
				+ "	listening_rate, "
				+ "	user_id "
				+ "FROM  "
				+ "	t_candidate_language tcl "
				+ " WHERE"
				+ " user_id = :candidate ";
		final List<?> languageObjs = this.em.createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		final List<CandidateLanguage> candidateLanguageList = new ArrayList<>();
		if(languageObjs.size() > 0) {
			for(Object languageObj : languageObjs) {
				final Object[] languageArr = (Object[]) languageObj;
				final CandidateLanguage candidateLanguage = new CandidateLanguage();
				
				candidateLanguage.setId(languageArr[0].toString());
				candidateLanguage.setLanguageName(languageArr[1].toString());
				candidateLanguage.setWritingRate(languageArr[2].toString());
				candidateLanguage.setSpeakingRate(languageArr[3].toString());
				candidateLanguage.setListeningRate(languageArr[4].toString());
				
				final CandidateUser candidateUser = new CandidateUser();
				candidateUser.setId(languageArr[5].toString());
				candidateLanguage.setCandidateUser(candidateUser);
				
				candidateLanguageList.add(candidateLanguage);
				
			}
		}
		return candidateLanguageList;
		
	}
}
