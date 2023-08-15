package com.lawencon.jobportaladmin.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.HiredDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.hired.HiredInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.Hired;
import com.lawencon.jobportaladmin.util.DateUtil;

@Service
public class HiredService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private HiredDao hiredDao;
	
	@Autowired
	private ApplicantDao applicantDao;
	
	private InsertResDto insertHired(HiredInsertReqDto hiredData) {
		final InsertResDto resDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			final Applicant applicant = applicantDao.getById(Applicant.class, hiredData.getApplicantId());
			
			Hired hired = new Hired();
			hired.setApplicant(applicant);
			hired.setStartDate(DateUtil.parseStringToLocalDateTime(hiredData.getStartDate()));
		
			if(hiredData.getStartDate()!= null) {
				hired.setEndDate(DateUtil.parseStringToLocalDateTime(hiredData.getEndDate()));
			}
			
			
			
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return resDto;
	}
	
	
}
