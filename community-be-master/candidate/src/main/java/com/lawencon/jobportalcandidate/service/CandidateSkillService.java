package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateSkillDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateSkill;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Service
public class CandidateSkillService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CandidateSkillDao candidateSkillDao;
	
	@Autowired
	private CandidateUserDao candidateUserDao;
	
	public List<CandidateSkillResDto> getSkillsByCandidate(String id) {
		final List<CandidateSkillResDto> skillsDto = new ArrayList<>();
		final List<CandidateSkill> skills = candidateSkillDao.getByCandidate(id);
		
		for (int i=0; i<skills.size(); i++) {
			final CandidateSkillResDto skill = new CandidateSkillResDto();
			skill.setId(skills.get(i).getId());
			skill.setSkillName(skills.get(i).getSkillName());
			skill.setCandidateId(skills.get(i).getCandidateUser().getId());
			
			skillsDto.add(skill);
		}
		
		return skillsDto;
	}
	
	public InsertResDto insertSkill(CandidateSkillInsertReqDto data) {
		final CandidateSkill skill = new CandidateSkill();
		
		InsertResDto result = null; 
		
		try {
			em().getTransaction().begin();
			skill.setSkillName(data.getSkillName());
			
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			skill.setCandidateUser(candidate);
			
			candidateSkillDao.save(skill);
			
			result = new InsertResDto();
			result.setId(skill.getId());
			result.setMessage("Skill record added!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public UpdateResDto updateSkill(CandidateSkillUpdateReqDto data) {
		final CandidateSkill skill = candidateSkillDao.getById(CandidateSkill.class, data.getId());
		
		UpdateResDto result = null; 
		
		try {
			em().getTransaction().begin();
			skill.setId(data.getId());
			skill.setSkillName(data.getSkillName());
			
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			skill.setCandidateUser(candidate);
			
			candidateSkillDao.save(skill);
			
			result = new UpdateResDto();
			result.setVersion(skill.getVersion());
			result.setMessage("Skill record updated!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
	
	public DeleteResDto deleteSkill(String id) {
		candidateSkillDao.deleteById(CandidateSkill.class, id);
		
		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Skill has been removed");
		
		return response;
	}
}
