package com.lawencon.jobportaladmin.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dao.ReviewDao;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.review.ReviewUpdateScoreReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.model.Review;

@Service
public class ReviewService extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ApplicantDao applicantDao;
	
	
	public UpdateResDto updateReviewScore(ReviewUpdateScoreReqDto data) {
		final UpdateResDto resDto = new UpdateResDto();
		System.out.println(data.getApplicantCode()+" =========");
		
		try {
			em().getTransaction().begin();
			System.out.println(data.getApplicantCode()+" =========");
			final Applicant applicant = applicantDao.getByCode(data.getApplicantCode());
			Review review = reviewDao.getByApplicant(applicant.getId());
			review.setScore(Float.valueOf(data.getScores().floatValue()));
			review.setApplicant(applicant);
			
			review = reviewDao.saveAndFlush(review);
		
			em().getTransaction().commit();
			
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("Error");
			
		}
		
		
		return resDto;
	}
	
}
