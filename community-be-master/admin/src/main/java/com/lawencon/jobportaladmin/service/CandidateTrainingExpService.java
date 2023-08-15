package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CandidateTrainingExpDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dto.DeleteResDto;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.candidatetrainingexp.CandidateTrainingExpInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatetrainingexp.CandidateTrainingExpResDto;
import com.lawencon.jobportaladmin.dto.candidatetrainingexp.CandidateTrainingExpUpdateReqDto;
import com.lawencon.jobportaladmin.model.CandidateTrainingExp;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateTrainingExpService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}


	@Autowired
	private CandidateUserDao candidateUserDao;

	@Autowired
	private CandidateTrainingExpDao trainingDao;

	@Autowired
	private PrincipalService<String> principalService;

	public List<CandidateTrainingExpResDto> getAllTrainingExpByCandidate(String id) {
		final List<CandidateTrainingExpResDto> trainingExpResList = new ArrayList<>();
		final List<CandidateTrainingExp> trainingExp = trainingDao.getByCandidate(id);
		for (int i = 0; i < trainingExp.size(); i++) {
			final CandidateTrainingExpResDto trainingExpRes = new CandidateTrainingExpResDto();
			trainingExpRes.setTrainingName(trainingExp.get(i).getTrainingName());
			trainingExpRes.setOrganizationName(trainingExp.get(i).getOrganizationName());
			trainingExpRes.setId(trainingExp.get(i).getId());
			trainingExpRes.setDescription(trainingExp.get(i).getDescription());
			trainingExpRes.setStartDate(trainingExp.get(i).getStartDate().toString());
			trainingExpRes.setEndDate(trainingExp.get(i).getEndDate().toString());

			trainingExpResList.add(trainingExpRes);
		}
		return trainingExpResList;
	}

	public InsertResDto insertCandidateTrainingExp(CandidateTrainingExpInsertReqDto data) {
		InsertResDto insertRes = null;
		try {
			em().getTransaction().begin();
			final CandidateTrainingExp trainingExp = new CandidateTrainingExp();
			trainingExp.setCreatedBy(principalService.getAuthPrincipal());
			trainingExp.setTrainingName(data.getTrainingName());
			trainingExp.setOrganizationName(data.getOrganizationName());
			trainingExp.setStartDate(LocalDate.parse(data.getStartDate().toString()));
			trainingExp.setEndDate(LocalDate.parse(data.getEndDate().toString()));
			trainingExp.setDescription(data.getDescription());
			final CandidateUser candidateUser = candidateUserDao.getByEmail(data.getEmail());
			trainingExp.setCandidateUser(candidateUser);

			final CandidateTrainingExp trainingId = trainingDao.save(trainingExp);
			insertRes = new InsertResDto();
			insertRes.setId(trainingId.getId());
			insertRes.setMessage("Training Exp record added!");
			em().getTransaction().commit();

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertRes;
	}

	public UpdateResDto updateCandidateTrainingExp(CandidateTrainingExpUpdateReqDto data) {
		final UpdateResDto updateRes = new UpdateResDto();
		try {
			em().getTransaction().begin();
			final CandidateTrainingExp trainingExp = trainingDao.getById(CandidateTrainingExp.class, data.getId());
			trainingExp.setUpdatedBy(principalService.getAuthPrincipal());
			trainingExp.setTrainingName(data.getTrainingName());
			trainingExp.setOrganizationName(data.getOrganizationName());
			trainingExp.setStartDate(LocalDate.parse(data.getStartDate().toString()));
			trainingExp.setEndDate(LocalDate.parse(data.getEndDate().toString()));

			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class,
					principalService.getAuthPrincipal());
			trainingExp.setCandidateUser(candidateUser);

			final CandidateTrainingExp trainingId = trainingDao.save(trainingExp);

			updateRes.setMessage("Candidate Training Exp Insert Success");
			updateRes.setVersion(trainingId.getVersion());
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return updateRes;
	}

	public DeleteResDto deleteCandidateTrainingExp(String id) {
		trainingDao.deleteById(CandidateTrainingExp.class, id);

		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Training Exp Success");
		return deleteRes;
	}
}
