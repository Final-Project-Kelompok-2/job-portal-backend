package com.lawencon.jobportaladmin.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CandidateProfileDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.candidateuser.CandidateUserInsertReqDto;
import com.lawencon.jobportaladmin.model.CandidateProfile;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateUserService {

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	
	@Autowired
	private PrincipalService<String> principalService;
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public InsertResDto insertCandidateuser(CandidateUserInsertReqDto candidateData) {
		final InsertResDto insertResDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			
			
			CandidateUser candidateUser = new CandidateUser();
			candidateUser.setUserEmail(candidateData.getUserEmail());
			
			CandidateProfile candidateProfile =new CandidateProfile();
			candidateProfile.setFullname(candidateData.getProfile().getFullname());
			candidateProfile = candidateProfileDao.save(candidateProfile);
			candidateUser.setCandidateProfile(candidateProfile);
			candidateUser = candidateUserDao.save(candidateUser);
			
			
			
			
			
			
			em().getTransaction().commit();
			
			
			
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		
		return insertResDto;
	}
	
}
