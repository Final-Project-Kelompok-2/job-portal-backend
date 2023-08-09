package com.lawencon.jobportaladmin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.OwnedBenefit;

@Repository
public class OwnedBenefitDao extends AbstractJpaDao {

	public List<OwnedBenefit> getByJob(String id) {
		final String sql = "SELECT tb.benefit_name from t_owned_benefit tob " 
				+ " INNER JOIN t_benefit tb ON tob.benefit_id  = tb.id "
				+ " WHERE tob.job_id = :id";

		final List<?> questionObj = ConnHandler.getManager().createNativeQuery(sql)
				.setParameter("id", id)
				.getResultList();

	}

}
