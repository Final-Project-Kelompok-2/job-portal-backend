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
		sqlb.append(" HiringStatus hs");
		sqlb.append("WHERE ");
		sqlb.append(" hs.statusCode = :statusCode");
		
		final String sql = "SELECT "
				+ " hs.id, "
				+ " hs.statusCode, "
				+ " hs.statusName, "
				+ " hs.version "
				+ " FROM HiringStatus hs"
				+ " WHERE hs.statusCode = :statusCode";
		
		final Object hiringStatusObj = this.em().createQuery(sqlb.toString()).setParameter("statusCode", statusCode).getSingleResult();
		
		final Object[] hiringStatusArr = (Object[]) hiringStatusObj;
		HiringStatus hiringStatus = null;
		
		if (hiringStatusArr.length > 0) {
			hiringStatus = new HiringStatus();
			
			hiringStatus.setId(hiringStatusArr[0].toString());
			hiringStatus.setStatusCode(hiringStatusArr[1].toString());
			hiringStatus.setStatusName(hiringStatusArr[2].toString());
			hiringStatus.setCreatedBy(hiringStatusArr[3].toString());
			hiringStatus.setCreatedAt(LocalDateTime.parse(hiringStatusArr[4].toString()));
			hiringStatus.setIsActive(Boolean.valueOf(hiringStatusArr[7].toString()));
			hiringStatus.setVersion(Integer.valueOf(hiringStatusArr[8].toString()));
		}
		
		return hiringStatus;
	}
	
}
