package com.lawencon.jobportaladmin.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateStatus;

@Repository
public class CandidateStatusDao extends AbstractJpaDao{
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	public CandidateStatus getByCode(String code) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, ");
		sql.append("statusCode , statusName ");
		sql.append("createdBy,createdAt, ");
		sql.append("isActive , version ");
		sql.append("FROM CandidateStatus cs ");
		sql.append("WHERE statusCode = :code ");
		
		final Object statusObj = em().createQuery(sql.toString())
				.setParameter("code", code)
				.getSingleResult();
		final Object[] statusArr = (Object[])statusObj;
		CandidateStatus status = null;
		if(statusArr.length > 0) {
			status = new CandidateStatus();
			status.setId(statusArr[0].toString());
			status.setStatusCode(statusArr[1].toString());
			status.setStatusName(statusArr[2].toString());
			status.setCreatedBy(statusArr[3].toString());
			status.setCreatedAt(LocalDateTime.parse(statusArr[4].toString()));
			status.setIsActive(Boolean.valueOf(statusArr[5].toString()));
			status.setVersion(Integer.valueOf(statusArr[6].toString()));
		}
		return status;
				
		
	}
}
