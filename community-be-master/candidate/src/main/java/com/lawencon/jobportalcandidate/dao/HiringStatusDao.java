package com.lawencon.jobportalcandidate.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.HiringStatus;

@Repository
public class HiringStatusDao extends AbstractJpaDao  {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
		
	public HiringStatus getByCode(String code) {
		final String sql = "SELECT "
				+ "	hs.id, "
				+ "	hs.statusCode, "
				+ " hs.statusName, "
				+ "	hs.createdBy, "
				+ "	hs.createdAt, "
				+ "	hs.isActive, "
				+ "	hs.version "
				+ "FROM "
				+ "	HiringStatus hs "
				+ "WHERE "
				+ "	hs.statusCode = :code";
		
		final Object hiringStatusObj = this.em().createQuery(sql).setParameter("code", code).getSingleResult();
		
		final Object[] hiringStatusArr = (Object[]) hiringStatusObj;
		HiringStatus hiringStatus = null;
		
		if (hiringStatusArr.length > 0) {
			hiringStatus = new HiringStatus();
			
			hiringStatus.setId(hiringStatusArr[0].toString());
			hiringStatus.setStatusCode(hiringStatusArr[1].toString());
			hiringStatus.setStatusName(hiringStatusArr[2].toString());
			hiringStatus.setCreatedBy(hiringStatusArr[3].toString());
			hiringStatus.setCreatedAt(LocalDateTime.parse(hiringStatusArr[4].toString()));
			hiringStatus.setIsActive(Boolean.valueOf(hiringStatusArr[5].toString()));
			hiringStatus.setVersion(Integer.valueOf(hiringStatusArr[6].toString()));
		}
		
		return hiringStatus;
	}
}
