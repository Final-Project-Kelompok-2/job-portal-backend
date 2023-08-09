package com.lawencon.jobportaladmin.dao;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
=======
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Benefit;
import com.lawencon.jobportaladmin.model.OwnedBenefit;
>>>>>>> 26a13ee290e67476d5486226e7480926c508caeb

@Repository
public class OwnedBenefitDao extends AbstractJpaDao {

<<<<<<< HEAD
=======
	private EntityManager em = ConnHandler.getManager();
	
	public List<OwnedBenefit> getByJob(String id) {
		final String sql = "SELECT tb.benefit_name from t_owned_benefit tob " 
				+ " INNER JOIN t_benefit tb ON tob.benefit_id = tb.id "
				+ " WHERE tob.job_id = :id";

		final List<?> ownedBenefitObjs = em.createNativeQuery(sql)
				.setParameter("id", id)
				.getResultList();

		final List<OwnedBenefit> ownedBenefits= new ArrayList<>();
		
		if(ownedBenefitObjs.size()>0) {
			for(Object ownedBenefitObj : ownedBenefitObjs) {
				final Object[] ownedBenefitObjArr = (Object []) ownedBenefitObj;
				final Benefit benefit = new Benefit();
				benefit.setBenefitName(ownedBenefitObjArr[0].toString());
				
				final OwnedBenefit ownedBenefit = new OwnedBenefit();
				ownedBenefit.setBenefit(benefit);
				
				ownedBenefits.add(ownedBenefit);
			}
		}
		
		return ownedBenefits;
		
	}

>>>>>>> 26a13ee290e67476d5486226e7480926c508caeb
}
