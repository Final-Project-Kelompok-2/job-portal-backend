package com.lawencon.jobportaladmin.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateProjectExp;

@Repository
public class CandidateProjectExpDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<CandidateProjectExp> getByCandidate(String id) {
		final List<CandidateProjectExp> projects = new ArrayList<>();
		
		final StringBuilder sql = new StringBuilder(); 
				sql.append ("SELECT ");
				sql.append ("	tcpe.id AS project_id, ");
				sql.append ("	project_name, ");
				sql.append ("	project_url, ");
				sql.append ("	description, ");
				sql.append ("	start_date, ");
				sql.append ("	end_date ");
				sql.append ("FROM ");
				sql.append ("	t_candidate_project_exp tcpe ");
				sql.append ("WHERE ");
				sql.append ("	user_id = :candidate");
		
		final List<?> projectObjs = em().createNativeQuery(sql.toString())
				.setParameter("candidate", id)
				.getResultList();
		
		if (projectObjs.size() > 0) {
			for (Object projectObj : projectObjs) {
				final Object[] projectArr = (Object[]) projectObj;
				final CandidateProjectExp project = new CandidateProjectExp();
				project.setId(projectArr[0].toString());
				project.setProjectName(projectArr[1].toString());
				project.setProjectUrl(projectArr[2].toString());
				project.setDescription(projectArr[3].toString());
				project.setStartDate(Timestamp.valueOf(projectArr[4].toString()).toLocalDateTime());
				project.setEndDate(Timestamp.valueOf(projectArr[5].toString()).toLocalDateTime());
				
				projects.add(project);
			}
		}
		
		return projects;
	}
	
	public List<CandidateProjectExp> getByCandidateEmail(String email){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cpe ");
		sql.append("FROM CandidateProjectExp cpe ");
		sql.append("INNER JOIN CandidateUser cu ");
		sql.append("WHERE cu.userEmail = :email ");
		final List<CandidateProjectExp> projectExpList = em().createQuery(sql.toString(),CandidateProjectExp.class)
				.setParameter("email", email)
				.getResultList();
		return projectExpList;
	}
}
