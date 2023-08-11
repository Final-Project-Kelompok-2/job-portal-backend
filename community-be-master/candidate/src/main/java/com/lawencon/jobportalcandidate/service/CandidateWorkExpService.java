package com.lawencon.jobportalcandidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.CandidateWorkExpDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpResDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.CandidateWorkExp;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateWorkExpService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateWorkExpDao candidateWorkExpDao;

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private PrincipalService<String> principalService;

	public List<CandidateWorkExpResDto> getWorkByCandidate(String id) {
		final List<CandidateWorkExpResDto> worksDto = new ArrayList<>();
		final List<CandidateWorkExp> works = candidateWorkExpDao.getByCandidate(id);

		for (int i = 0; i < works.size(); i++) {
			final CandidateWorkExpResDto work = new CandidateWorkExpResDto();
			work.setId(works.get(i).getId());
			work.setPositionName(works.get(i).getPositionName());
			work.setCompanyName(works.get(i).getCompanyName());
			work.setAddress(works.get(i).getAddress());
			work.setResponsibility(works.get(i).getResponsibility());
			work.setReasonLeave(works.get(i).getReasonLeave());
			work.setLastSalary(works.get(i).getLastSalary());
			work.setStartDate(works.get(i).getStartDate().toString());
			work.setEndDate(works.get(i).getEndDate().toString());

			worksDto.add(work);
		}

		return worksDto;
	}

	public InsertResDto insertWorksExperience(CandidateWorkExpInsertReqDto data) {
		final CandidateWorkExp work = new CandidateWorkExp();

		InsertResDto result = null;

		try {
			em().getTransaction().begin();
			work.setPositionName(data.getPositionName());
			work.setCompanyName(data.getCompanyName());
			work.setAddress(data.getAddress());
			work.setResponsibility(data.getResponsibility());
			work.setReasonLeave(data.getReasonLeave());
			work.setLastSalary(data.getLastSalary());
			work.setStartDate(LocalDate.parse(data.getStartDate().toString()));
			work.setEndDate(LocalDate.parse(data.getEndDate().toString()));

			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, principalService.getAuthPrincipal());
			work.setCandidateUser(candidate);
			work.setCreatedBy(principalService.getAuthPrincipal());

			candidateWorkExpDao.save(work);

			result = new InsertResDto();
			result.setId(work.getId());
			result.setMessage("Working Experience record added!");

			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return result;
	}
	
	public UpdateResDto updateWorksExperience(CandidateWorkExpUpdateReqDto data) {
		final CandidateWorkExp work = candidateWorkExpDao.getById(CandidateWorkExp.class, data.getId());

		UpdateResDto result = null;

		try {
			em().getTransaction().begin();
			work.setId(data.getId());
			work.setPositionName(data.getPositionName());
			work.setCompanyName(data.getCompanyName());
			work.setAddress(data.getAddress());
			work.setResponsibility(data.getResponsibility());
			work.setReasonLeave(data.getReasonLeave());
			work.setLastSalary(data.getLastSalary());
			work.setStartDate(LocalDate.parse(data.getStartDate().toString()));
			work.setEndDate(LocalDate.parse(data.getEndDate().toString()));

			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, principalService.getAuthPrincipal());
			work.setCandidateUser(candidate);
			work.setUpdatedBy(principalService.getAuthPrincipal());

			candidateWorkExpDao.saveAndFlush(work);

			result = new UpdateResDto();
			result.setVersion(work.getVersion());
			result.setMessage("Working Experience record updated!");

			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return result;
	}
	
	public DeleteResDto deleteWorkExperience(String id) {
		candidateWorkExpDao.deleteById(CandidateWorkExp.class, id);
		
		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Working Experience Has Been Removed");
		
		return response;
	}
}

