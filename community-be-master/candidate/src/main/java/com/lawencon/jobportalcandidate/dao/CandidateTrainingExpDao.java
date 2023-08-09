package com.lawencon.jobportalcandidate.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateTrainingExp;

@Repository
public class CandidateTrainingExpDao extends AbstractJpaDao{
	private EntityManager em = ConnHandler.getManager();
	
	public List<CandidateTrainingExp> getByCandidate(String id) {
		final List<CandidateTrainingExp> trainings = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
		final String sql = "SELECT "
				+ "	tcte.id AS training_id, "
				+ "	organization_name, "
				+ "	training_name, "
				+ "	description, "
				+ "	start_date, "
				+ "	end_date "
				+ "FROM "
				+ "	t_candidate_training_exp tcte "
				+ "WHERE "
				+ "	user_id = :candidate";
		
		final List<?> trainingObjs = this.em.createNativeQuery(sql)
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
}
