package com.lawencon.jobportalcandidate.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;

@Repository
public class CandidateProfileDao extends AbstractJpaDao{
	private EntityManager em = ConnHandler.getManager();
}
