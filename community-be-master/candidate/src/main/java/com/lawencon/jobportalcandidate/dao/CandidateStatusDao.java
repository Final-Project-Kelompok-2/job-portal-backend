package com.lawencon.jobportalcandidate.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateStatus;

@Repository
public class CandidateStatusDao extends AbstractJpaDao {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public CandidateStatus getByCode(String code) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cu FROM CandidateStatus cs WHERE cs.statusCode= : code");

		final CandidateStatus candidateStatus = em().createQuery(sql.toString(), 
				CandidateStatus.class)
				.setParameter(":code", code).getSingleResult();

		return candidateStatus;
	}

}
