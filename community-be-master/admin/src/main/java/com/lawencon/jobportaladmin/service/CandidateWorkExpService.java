package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.CandidateWorkExpDao;
import com.lawencon.jobportaladmin.dto.DeleteResDto;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.candidateworkexp.CandidateWorkExpInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateworkexp.CandidateWorkExpResDto;
import com.lawencon.jobportaladmin.dto.candidateworkexp.CandidateWorkExpUpdateReqDto;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.CandidateWorkExp;
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
		final InsertResDto result = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateWorkExp work = new CandidateWorkExp();
			work.setPositionName(data.getPositionName());
			work.setCompanyName(data.getCompanyName());
			work.setAddress(data.getAddress());
			work.setResponsibility(data.getResponsibility());
			work.setReasonLeave(data.getReasonLeave());
			work.setLastSalary(data.getLastSalary());
			work.setStartDate(LocalDate.parse(data.getStartDate().toString()));
			work.setEndDate(LocalDate.parse(data.getEndDate().toString()));

			final CandidateUser candidate = candidateUserDao.getByEmail(data.getEmail());
			work.setCandidateUser(candidate);
			work.setCreatedBy(principalService.getAuthPrincipal());

			candidateWorkExpDao.save(work);
			
			result.setId(work.getId());
			result.setMessage("Working Experience record added!");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
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
