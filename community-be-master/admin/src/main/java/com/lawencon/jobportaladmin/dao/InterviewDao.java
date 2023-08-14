package com.lawencon.jobportaladmin.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.jobportaladmin.model.Interview;

@Repository
public class InterviewDao extends AbstractJpaDao{

	public Interview getByApplicantCode(String code) {
		final StringBuilder sql = new StringBuilder();
		
		final Interview interview = new Interview();
		return interview;
	}
	
	
}
