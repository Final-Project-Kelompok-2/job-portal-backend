package com.lawencon.jobportalcandidate.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.Question;

@Repository
public class QuestionDao extends AbstractJpaDao{
	
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public Question getByCode(String code) {
		final String sql = "SELECT q.id, q.questionCode,q.questionDetail, "
				+ "q.createdBy , q.createdAt , q.isActive , q.version "
				+ "FROM Question q "
				+ "WHERE q.questionCode = :code";
		final Object questionObj = em().createQuery(sql)
				.setParameter("code", code)
				.getSingleResult();
		
		final Object[] questionArr = (Object[]) questionObj;
		
		final Question question = new Question();
		question.setId(questionArr[0].toString());
		question.setQuestionCode(questionArr[1].toString());
		question.setQuestionDetail(questionArr[2].toString());
		question.setCreatedBy(questionArr[3].toString());
		question.setCreatedAt(LocalDateTime.parse(questionArr[4].toString()));
		question.setIsActive(Boolean.valueOf(questionArr[5].toString()));
		question.setVersion(Integer.valueOf(questionArr[6].toString()));
		
		return question;
		
				
	}
}
