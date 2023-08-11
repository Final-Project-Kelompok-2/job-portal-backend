package com.lawencon.jobportalcandidate.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateTrainingExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpResDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateTrainingExp;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Service
public class CandidateTrainingExpService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateTrainingExpDao trainingDao;

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
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateTrainingExp trainingExp = new CandidateTrainingExp();
			trainingExp.setCreatedBy("ID Principal");
			trainingExp.setTrainingName(data.getTrainingName());
			trainingExp.setOrganizationName(data.getOrganizationName());
			trainingExp.setStartDate(LocalDateTime.parse(data.getStartDate().toString()));
			trainingExp.setEndDate(LocalDateTime.parse(data.getEndDate().toString()));

			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			trainingExp.setCandidateUser(candidateUser);

			final CandidateTrainingExp trainingId = trainingDao.save(trainingExp);
			insertRes.setId(trainingId.getId());
			insertRes.setMessage("Candidate Training Exp Insert Success");
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
			trainingExp.setUpdatedBy("ID Principal");
			trainingExp.setTrainingName(data.getTrainingName());
			trainingExp.setOrganizationName(data.getOrganizationName());
			trainingExp.setStartDate(LocalDateTime.parse(data.getStartDate().toString()));
			trainingExp.setEndDate(LocalDateTime.parse(data.getEndDate().toString()));

			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, "ID Principal");
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