package com.lawencon.jobportalcandidate.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.ApplicantDao;
import com.lawencon.jobportalcandidate.dao.HiringStatusDao;
import com.lawencon.jobportalcandidate.dao.JobDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.jobportalcandidate.dto.applicant.ApplicantResDto;
import com.lawencon.jobportalcandidate.model.Applicant;
import com.lawencon.jobportalcandidate.model.HiringStatus;
import com.lawencon.jobportalcandidate.model.Job;
import com.lawencon.jobportalcandidate.util.GenerateCode;
import com.lawencon.security.principal.PrincipalService;

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
	private PrincipalService<String> principalService;

	public List<ApplicantResDto> getApplicantByCandidate(String id) {
		final List<Applicant> applicantList = applicantDao.getApplicantByCandidate(id);
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

	public InsertResDto insertApplicant(ApplicantInsertReqDto data) {
		final LocalDateTime currentDate = LocalDateTime.now();
		final Applicant applicant = new Applicant();
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			applicant.setApplicantCode(GenerateCode.generateTicket(5));
			applicant.setAppliedDate(currentDate);

			final Job job = jobDao.getById(Job.class, data.getJobId());
			applicant.setJob(job);

			final HiringStatus hiringStatus = hiringStatusDao.getById(HiringStatus.class, data.getStatusId());
			applicant.setStatus(hiringStatus);
			applicant.setCreatedBy(principalService.getAuthPrincipal());
			final Applicant applicantId = applicantDao.save(applicant);
			insertRes.setId(applicantId.getId());
			insertRes.setMessage("Applicant Insert Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertRes;
	}

}
