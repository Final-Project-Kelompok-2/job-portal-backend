package com.lawencon.jobportalcandidate.dao;

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
		final StringBuilder sqlb = new StringBuilder();
			sqlb.append("SELECT ");
			sqlb.append(" hs ");
			sqlb.append("FROM ");
			sqlb.append(" HiringStatus hs ");
			sqlb.append(" WHERE ");
			sqlb.append(" hs.statusCode = :code");
			
//		final String sql = "SELECT "
//				+ "	hs.id, "
//				+ "	hs.statusCode, "
//				+ " hs.statusName, "
//				+ "	hs.version "
//				+ "FROM "
//				+ "	HiringStatus hs "
//				+ "WHERE "
//				+ "	hs.statusCode = :code";
<<<<<<< HEAD
//		
//		final Object hiringStatusObj = this.em().createQuery(sqlb.toString())
//				.setParameter("code", code)
//				.getSingleResult();
		final HiringStatus hiringStatus= this.em().createQuery(sqlb.toString(),HiringStatus.class)
				.setParameter("statusCode", code)
				.getSingleResult();
		
//		final Object[] hiringStatusArr = (Object[]) hiringStatusObj;
//		HiringStatus hiringStatus = null;
//		
//		if (hiringStatusArr.length > 0) {
//			hiringStatus = new HiringStatus();
//			
//			hiringStatus.setId(hiringStatusArr[0].toString());
//			hiringStatus.setStatusCode(hiringStatusArr[1].toString());
//			hiringStatus.setStatusName(hiringStatusArr[2].toString());
//			hiringStatus.setVersion(Integer.valueOf(hiringStatusArr[3].toString()));
//		}
=======
		
		final HiringStatus hiringStatus = this.em().createQuery(sqlb.toString(),HiringStatus.class)
				.setParameter("code", code)
				.getSingleResult();
		
>>>>>>> b5519995a3735acc35601bc251620f4dc5cd39b6
		
		return hiringStatus;
	}
}
