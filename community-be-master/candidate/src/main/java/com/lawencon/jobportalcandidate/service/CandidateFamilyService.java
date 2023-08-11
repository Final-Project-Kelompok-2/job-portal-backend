package com.lawencon.jobportalcandidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateFamilyDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyResDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateFamily;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateFamilyService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CandidateFamilyDao candidateFamilyDao;
	
	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private PrincipalService<String> principalService;
	
	public List<CandidateFamilyResDto> getFamilyByCandidate(String id) {
		final List<CandidateFamilyResDto> familiesDto = new ArrayList<>();
		final List<CandidateFamily> families = candidateFamilyDao.getFamilyByCandidate(id);
		
		for (int i=0; i<families.size(); i++) {
			final CandidateFamilyResDto family = new CandidateFamilyResDto();
			family.setId(families.get(i).getId());
			family.setFullname(families.get(i).getFullname());
			family.setRelationship(families.get(i).getRelationship());
			family.setDegreeName(families.get(i).getDegreeName());
			family.setOccupation(families.get(i).getOccupation());
			family.setBirthDate(families.get(i).getBirthDate().toString());
			family.setBirthPlace(families.get(i).getBirthPlace());
			
			familiesDto.add(family);
		}
		
		return familiesDto;
	}
	
	public InsertResDto insertFamily(CandidateFamilyInsertReqDto data) {
		final CandidateFamily family = new CandidateFamily();

		InsertResDto result = null;
		
		try {
			em().getTransaction().begin();
			family.setFullname(data.getFullname());
			family.setRelationship(data.getRelationship());
			family.setDegreeName(data.getDegreeName());
			family.setOccupation(data.getOccupation());
			family.setBirthDate(LocalDate.parse(data.getBirthDate().toString()));
			family.setBirthPlace(data.getBirthPlace());
			family.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			family.setCandidateUser(candidate);
			
			candidateFamilyDao.save(family);
			
			result = new InsertResDto();
			result.setId(family.getId());
			result.setMessage("Family record added!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public UpdateResDto updateFamily(CandidateFamilyUpdateReqDto data) {
		final CandidateFamily family = candidateFamilyDao.getById(CandidateFamily.class, data.getId());

		UpdateResDto result = null;
		
		try {
			em().getTransaction().begin();
			family.setId(data.getId());
			family.setFullname(data.getFullname());
			family.setRelationship(data.getRelationship());
			family.setDegreeName(data.getDegreeName());
			family.setOccupation(data.getOccupation());
			family.setBirthDate(LocalDate.parse(data.getBirthDate().toString()));
			family.setBirthPlace(data.getBirthPlace());
			family.setUpdatedBy(principalService.getAuthPrincipal());
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			family.setCandidateUser(candidate);
			candidateFamilyDao.saveAndFlush(family);
			
			result = new UpdateResDto();
			result.setVersion(family.getVersion());
			result.setMessage("Family record updated!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public DeleteResDto deleteFamily(String id) {
		candidateFamilyDao.deleteById(CandidateFamily.class, id);
		
		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Family has been removed");
		
		return response;
	}
}
