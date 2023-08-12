package com.lawencon.jobportaladmin.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateUser;

@Repository
public class CandidateUserDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	
	public CandidateUser getByEmail(String candidateEmail) {
		final String sql =  "SELECT "
				+ "	cu.id, "
				+ "	cu.version "
				+ "FROM "
				+ "	CandidateUser cu "
				+ "WHERE "
				+ "	cu.userEmail= :candidateEmail";
		
		final CandidateUser candidateUser = this.em().createQuery(sql,CandidateUser.class)
				.setParameter("candidateEmail", candidateEmail)
				.getSingleResult();
		
		return candidateUser;
		
	}
	
	
}
