package com.lawencon.jobportaladmin.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.HiringStatus;
import com.lawencon.jobportaladmin.model.Job;

@Repository
public class HiringStatusDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	
	public HiringStatus getByCode(String statusCode ) {
		final String sql = "SELECT "
				+ " hs.id, "
				+ " hs.statusCode, "
				+ " hs.statusName, "
				+ " hs.version "
				+ " FROM HiringStatus hs"
				+ " WHERE hs.statusCode = :statusCode";
		
		final HiringStatus hiringStatus = em().createQuery(sql,HiringStatus.class)
				.setParameter("statusCode", statusCode)
				.getSingleResult();
		
		return hiringStatus;
	}
	
}
