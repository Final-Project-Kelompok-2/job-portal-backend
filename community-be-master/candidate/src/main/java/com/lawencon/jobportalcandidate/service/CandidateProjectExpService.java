package com.lawencon.jobportalcandidate.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateProjectExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpResDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateProjectExp;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateProjectExpService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateProjectExpDao projectExpDao;
	@Autowired
	private PrincipalService<String> principalService;
	
	public List<CandidateProjectExpResDto> getProjectExpByCandidate(String id){
		final List<CandidateProjectExpResDto> projectExpResList = new ArrayList<>();
		final List<CandidateProjectExp> projectExp = projectExpDao.getByCandidate(id);
		for(int i = 0 ; i < projectExp.size() ; i++) {
			final CandidateProjectExpResDto projectExpRes = new CandidateProjectExpResDto();
			projectExpRes.setDescription(projectExp.get(i).getDescription());
			projectExpRes.setEndDate(projectExp.get(i).getEndDate().toString());
			projectExpRes.setStartDate(projectExp.get(i).getStartDate().toString());
			projectExpRes.setProjectName(projectExp.get(i).getProjectName());
			projectExpRes.setProjectUrl(projectExp.get(i).getProjectUrl());
			projectExpRes.setId(projectExp.get(i).getId());
			
			projectExpResList.add(projectExpRes);
		}
		
		return projectExpResList;
	}
	
	public InsertResDto insertCandidateProjectExp(CandidateProjectExpInsertReqDto data) {
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateProjectExp projectExp = new CandidateProjectExp();
			projectExp.setProjectName(data.getProjectName());
			projectExp.setDescription(data.getDescription());
			projectExp.setProjectUrl(data.getProjectUrl());
			projectExp.setStartDate(LocalDateTime.parse(data.getStartDate()));
			projectExp.setEndDate(LocalDateTime.parse(data.getEndDate()));
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			projectExp.setCandidateUser(candidateUser);
			projectExp.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateProjectExp projectExpId = projectExpDao.save(projectExp);
			insertRes.setId(projectExpId.getId());
			insertRes.setMessage("Candidate Project Exp Insert Success");
			em().getTransaction().commit();
		}catch(Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return insertRes;
	}
	
	public UpdateResDto updateCandidateProjectExp(CandidateProjectExpUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		try {
			em().getTransaction().begin();
			final CandidateProjectExp projectExp = projectExpDao.getById(CandidateProjectExp.class, data.getId());
			projectExp.setProjectName(data.getProjectName());
			projectExp.setDescription(data.getDescription());
			projectExp.setProjectUrl(data.getProjectUrl());
			projectExp.setStartDate(LocalDateTime.parse(data.getStartDate()));
			projectExp.setEndDate(LocalDateTime.parse(data.getEndDate()));
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			projectExp.setCandidateUser(candidateUser);
			projectExp.setUpdatedBy(principalService.getAuthPrincipal());
			final CandidateProjectExp projectExpId = projectExpDao.saveAndFlush(projectExp);
			updateResDto.setVersion(projectExpId.getVersion());
			updateResDto.setMessage("Candidate Project Exp Update Success");
			em().getTransaction().commit();
		}catch(Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return updateResDto;
	}
	
	public DeleteResDto deleteCandidateProjectExp(String id) {
		projectExpDao.deleteById(CandidateProjectExp.class, id);
		
		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Project Success");
		return deleteRes;
	}

}
