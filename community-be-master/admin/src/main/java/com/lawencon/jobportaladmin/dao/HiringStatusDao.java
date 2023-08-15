package com.lawencon.jobportaladmin.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.HiringStatus;

@Repository
public class HiringStatusDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	
	public HiringStatus getByCode(String statusCode ) {
		final StringBuilder sqlb = new StringBuilder();
		sqlb.append("SELECT ");
		sqlb.append(" hs ");
		sqlb.append("FROM ");
		sqlb.append(" HiringStatus hs ");
		sqlb.append("WHERE ");
		sqlb.append(" hs.statusCode = :statusCode");
		
		final String sql = "SELECT "
				+ " hs.id, "
				+ " hs.statusCode, "
				+ " hs.statusName, "
				+ " hs.version "
				+ " FROM HiringStatus hs"
				+ " WHERE hs.statusCode = :statusCode";
		
		final HiringStatus hiringStatus = this.em().createQuery(sqlb.toString(),HiringStatus.class).setParameter("statusCode", statusCode).getSingleResult();
		
	
		return hiringStatus;
	}
	
}
