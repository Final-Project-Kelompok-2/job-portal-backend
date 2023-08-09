package com.lawencon.jobportaladmin.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Assesment;
import com.lawencon.jobportaladmin.model.Review;

@Repository
public class AssesmentDao extends AbstractJpaDao {

	public Assesment getByApplicant(String applicantId) {
		Assesment assesment = null ;

		final String sql = 
				"SELECT notes , score  "
				+ "FROM t_assesment ta "
				+ "INNER JOIN t_applicant tap "
				+ "ON tap.id = ta.applicant_id  "
				+ "INNER JOIN t_review tr "
				+ "ON tr.applicant_id = tap.id "
				+ "WHERE ta.applicant_id = :applicantId";

		try {
			final Object assObj = ConnHandler.getManager().createNativeQuery(sql).setParameter("applicantId", applicantId)
					.getSingleResult();

			final Object[] assArr = (Object[]) assObj;

			if (assArr.length > 0) {
				
				final Review review = new Review();
				review.setNotes(assArr[0].toString());
				review.setScore(Float.valueOf(assArr[1].toString()));
				
				
				
				assesment = new Assesment();
//				assesment;
//
//				final User student = new User();
//				student.setId(Long.valueOf(attArr[1].toString()));
//
//				final Session session = new Session();
//				session.setId(Long.valueOf(attArr[2].toString()));
//
//				attendance.setIsApproved(Boolean.valueOf(attArr[3].toString()));
//				attendance.setIsAttended(Boolean.valueOf(attArr[4].toString()));
//				attendance.setUser(student);
//				attendance.setSession(session);
			}

			return assesment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
