package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.HiringStatusDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantResDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.HiringStatus;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.util.DateUtil;


@Service
public class ApplicantService {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private ApplicantDao applicantDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private HiringStatusDao hiringStatusDao;
	
	@Autowired
	private CandidateUserDao candidateUserDao;

	
	public List<ApplicantResDto> getAllApplicantByJob(String id) {
		final List<Applicant> applicantList = applicantDao.getApplicantByJob(id);
		final List<ApplicantResDto> applicantListRes = new ArrayList<>();
		for (int i = 0; i < applicantList.size(); i++) {
			final ApplicantResDto applicantRes = new ApplicantResDto();
			applicantRes.setId(applicantList.get(i).getId());
			applicantRes.setApplicantCode(applicantList.get(i).getApplicantCode());
			applicantRes.setAppliedDate(applicantList.get(i).getAppliedDate().toString());
			applicantRes.setStatusId(applicantList.get(i).getStatus().getId());
			applicantRes.setStatusName(applicantList.get(i).getStatus().getStatusName());
			applicantRes.setJobId(applicantList.get(i).getJob().getId());
			applicantRes.setJobName(applicantList.get(i).getJob().getJobName());
			applicantRes.setCompanyName(applicantList.get(i).getJob().getCompany().getCompanyName());
			applicantListRes.add(applicantRes);
		}
		return applicantListRes;
	}
	
	public InsertResDto insertApplicant(ApplicantInsertReqDto applicantData) {
		final InsertResDto resDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			 Applicant newApplicant = new Applicant();
			newApplicant.setApplicantCode(applicantData.getApplicantCode());
			
			final Job job = jobDao.getByCode(applicantData.getJobCode());
			newApplicant.setJob(job);
			
			newApplicant.setAppliedDate(DateUtil.parseStringToLocalDateTime(applicantData.getAppliedDate()));
			
			final HiringStatus hiringStatus = hiringStatusDao.getByCode(applicantData.getStatusCode());
			newApplicant.setStatus(hiringStatus);
			
			final CandidateUser candidateUser = candidateUserDao.getByEmail(applicantData.getCandidateEmail());
			newApplicant.setCandidate(candidateUser);
			
			newApplicant = applicantDao.save(newApplicant);
			resDto.setId(newApplicant.getId());
			resDto.setMessage("Insert Applicant Success");
			em().getTransaction().commit();
			
			
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return resDto;
	}
}
