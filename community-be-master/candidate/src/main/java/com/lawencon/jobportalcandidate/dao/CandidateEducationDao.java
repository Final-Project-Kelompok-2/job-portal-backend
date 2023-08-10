package com.lawencon.jobportalcandidate.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateEducation;
import com.lawencon.jobportalcandidate.model.CandidateUser;


@Repository
public class CandidateEducationDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<CandidateEducation> getEducationByCandidate(String id){
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		final String sql = "SELECT  "
				+ "	tce.id, "
				+ "	degree_name, "
				+ "	instituition_name, "
				+ "	majors, "
				+ "	cgpa, "
				+ "	start_year, "
				+ "	end_year, "
				+ "	user_id, "
				+ "FROM  "
				+ "	t_candidate_education tce  "
				+ "INNER JOIN  "
				+ "	t_candidate_user tcu  "
				+ "ON "
				+ "	tcu.id = tce.user_id "
				+ "WHERE  "
				+ "	tce.user_id  = :candidate" ;

		final List<?>educationObjs = em().createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		final List<CandidateEducation> educationList = new ArrayList<>();
		if(educationObjs.size() > 0) {
			for(Object educationObj : educationObjs) {
				final Object[] educationArr = (Object[]) educationObj;
				final CandidateEducation candidateEducation = new CandidateEducation();
				
				candidateEducation.setId(educationArr[0].toString());
				candidateEducation.setDegreeName(educationArr[1].toString());
				candidateEducation.setInstitutionName(educationArr[2].toString());
				candidateEducation.setMajors(educationArr[3].toString());
				candidateEducation.setCgpa(Float.valueOf(educationArr[4].toString()));
				candidateEducation.setStartYear(LocalDateTime.parse(educationArr[5].toString(),formatter));
				candidateEducation.setEndYear(LocalDateTime.parse(educationArr[6].toString(),formatter));
				
				final CandidateUser candidateUser = new CandidateUser();
				candidateUser.setId(educationArr[7].toString());
				candidateEducation.setCandidateUser(candidateUser);
				
				educationList.add(candidateEducation);
			}
		}
		return educationList;
	}
	
}
