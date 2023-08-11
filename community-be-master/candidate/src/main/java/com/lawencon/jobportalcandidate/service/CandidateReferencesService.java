package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateReferencesDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesResDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateReferences;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateReferencesService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CandidateReferencesDao candidateRefDao;
	
	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private PrincipalService<String> principalService;
	
	public List<CandidateReferencesResDto> getReferencesByCandidate(String id) {
		final List<CandidateReferencesResDto> referencesDto = new ArrayList<>();
		final List<CandidateReferences> references = candidateRefDao.getByCandidate(id);
		
		for (int i=0; i<references.size(); i++) {
			final CandidateReferencesResDto reference = new CandidateReferencesResDto();
			reference.setId(references.get(i).getId());
			reference.setFullname(references.get(i).getFullName());
			reference.setRelationship(references.get(i).getRelationship());
			reference.setOccupation(references.get(i).getOccupation());
			reference.setPhoneNumber(references.get(i).getPhoneNumber());
			reference.setEmail(references.get(i).getEmail());
			reference.setCompany(references.get(i).getCompany());
			reference.setDescription(references.get(i).getDescription());
			
			referencesDto.add(reference);
		}
		
		return referencesDto;
	}
	
	public InsertResDto insertReference(CandidateReferencesInsertReqDto data) {
		final CandidateReferences reference = new CandidateReferences();

		InsertResDto result = null;
		
		try {
			em().getTransaction().begin();
			reference.setFullName(data.getFullname());
			reference.setRelationship(data.getRelationship());
			reference.setOccupation(data.getOccupation());
			reference.setPhoneNumber(data.getPhoneNumber());
			reference.setEmail(data.getEmail());
			reference.setCompany(data.getCompany());
			reference.setDescription(data.getDescription());
			reference.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			reference.setCandidateUser(candidate);
			
			result = new InsertResDto();
			result.setId(reference.getId());
			result.setMessage("Reference record added!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public UpdateResDto updateReferences(CandidateReferencesUpdateReqDto data) {
		final CandidateReferences reference = candidateRefDao.getById(CandidateReferences.class, data.getId());

		UpdateResDto result = null;
		
		try {
			em().getTransaction().begin();
			reference.setFullName(data.getFullname());
			reference.setRelationship(data.getRelationship());
			reference.setOccupation(data.getOccupation());
			reference.setPhoneNumber(data.getPhoneNumber());
			reference.setEmail(data.getEmail());
			reference.setCompany(data.getCompany());
			reference.setDescription(data.getDescription());
			reference.setUpdatedBy(principalService.getAuthPrincipal());
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			reference.setCandidateUser(candidate);
			
			result = new UpdateResDto();
			result.setVersion(reference.getVersion());
			result.setMessage("Reference record updated!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public DeleteResDto deleteReference(String id) {
		candidateRefDao.deleteById(CandidateReferences.class, id);
		
		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Reference has been removed");
		
		return response;
	}
}
