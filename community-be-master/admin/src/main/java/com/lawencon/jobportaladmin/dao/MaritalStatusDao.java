package com.lawencon.jobportaladmin.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.MaritalStatus;

@Repository
public class MaritalStatusDao extends AbstractJpaDao {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public MaritalStatus getByCode(String code) {
		final StringBuilder sqlb = new StringBuilder();
		sqlb.append("SELECT ");
		sqlb.append(" ms ");
		sqlb.append("FROM ");
		sqlb.append(" MaritalStatus ms ");
		sqlb.append("WHERE ");
		sqlb.append(" ms.maritalCode = :code");

		final Object statusObj = this.em().createQuery(sqlb.toString()).getSingleResult();

		final Object[] statusArr = (Object[]) statusObj;
		MaritalStatus status = null;
		
		if (statusArr.length > 0) {			
			status = new MaritalStatus();
			status.setId(statusArr[0].toString());
			status.setMaritalCode(statusArr[1].toString());
			status.setMaritalName(statusArr[2].toString());
			status.setCreatedBy(statusArr[3].toString());
			status.setCreatedAt(LocalDateTime.parse(statusArr[4].toString()));
			status.setIsActive(Boolean.valueOf(statusArr[5].toString()));
			status.setVersion(Integer.valueOf(statusArr[6].toString()));
		}

		return status;
	}
}
