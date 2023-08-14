package com.lawencon.jobportaladmin.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Review;

@Repository
public class ReviewDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public Review getByApplicant(String id) {
		final StringBuilder sqlb = new StringBuilder();
			sqlb.append("SELECT ");
			sqlb.append(" tr.notes, ");
			sqlb.append(" tr.score ");
			sqlb.append("FROM ");
			sqlb.append(" t_review tr");
			sqlb.append("WHERE ");
			sqlb.append(" tr.applicant_id = :id");
		
		final String sql ="SELECT tr.notes,tr.score FROM t_review tr WHERE tr.applicant_id = :id";
		
		final Object reviewObj = em().createNativeQuery(sqlb.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		final Object[] reviewObjArr = (Object[]) reviewObj;
		Review review = null;
		
		if(reviewObjArr.length>0) {
			review = new Review();
			review.setNotes(reviewObjArr[0].toString());
			review.setScore(Float.valueOf(reviewObjArr[1].toString()));
		}
		return review;
	}
	
}
