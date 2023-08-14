package com.lawencon.jobportaladmin.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateTrainingExp;

@Repository
public class CandidateTrainingExpDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<CandidateTrainingExp> getByCandidate(String id) {
		final List<CandidateTrainingExp> trainings = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
		final StringBuilder sql = new StringBuilder();
				sql.append ("SELECT ");
				sql.append ("	tcte.id AS training_id, ");
				sql.append ("	organization_name, ");
				sql.append ("	training_name, ");
				sql.append ("	description, ");
				sql.append ("	start_date, ");
				sql.append ("	end_date ");
				sql.append ("FROM ");
				sql.append ("	t_candidate_training_exp tcte ");
				sql.append ("WHERE ");
				sql.append ("	user_id = :candidate");
		
		final List<?> trainingObjs = em().createNativeQuery(sql.toString())
				.setParameter("candidate", id)
				.getResultList();
		
		if (trainingObjs.size() > 0) {
			for (Object trainingObj : trainingObjs) {
				final Object[] trainingArr = (Object[]) trainingObj;
				final CandidateTrainingExp training = new CandidateTrainingExp();
				training.setId(trainingArr[0].toString());
				training.setOrganizationName(trainingArr[1].toString());
				training.setTrainingName(trainingArr[2].toString());
				training.setDescription(trainingArr[3].toString());
				training.setStartDate(LocalDateTime.parse(trainingArr[4].toString(), formatter));
				training.setEndDate(LocalDateTime.parse(trainingArr[5].toString(), formatter));
				
				trainings.add(training);
			}
		}
		
		return trainings;
	}
	
	public List<CandidateTrainingExp> getByCandidateEmail(String email){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cte ");
		sql.append("FROM CandidateTrainingExp cte ");
		sql.append("INNER JOIN CandidateUser cu ");
		sql.append("WHERE cu.userEmail = :email");
		final List<CandidateTrainingExp> trainingExpList = em().createQuery(sql.toString(),CandidateTrainingExp.class)
				.setParameter("email", email)
				.getResultList();
		return trainingExpList;
	}
	
}
