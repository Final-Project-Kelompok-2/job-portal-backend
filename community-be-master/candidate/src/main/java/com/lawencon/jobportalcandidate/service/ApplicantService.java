package com.lawencon.jobportalcandidate.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.config.JwtConfig;
import com.lawencon.jobportalcandidate.dao.ApplicantDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.HiringStatusDao;
import com.lawencon.jobportalcandidate.dao.JobDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.jobportalcandidate.dto.applicant.ApplicantResDto;
import com.lawencon.jobportalcandidate.model.Applicant;
import com.lawencon.jobportalcandidate.model.CandidateUser;
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
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CandidateUserDao candidateUserDao;

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
			applicant.setApplicantCode(GenerateCode.generateCode());
			
			data.setApplicantCode(applicant.getApplicantCode());
			applicant.setAppliedDate(currentDate);

			final Job job = jobDao.getById(Job.class, data.getJobId());
			applicant.setJob(job);
			
			data.setJobCode(job.getJobCode());

			final HiringStatus hiringStatus = hiringStatusDao.getById(HiringStatus.class, data.getStatusId());
			applicant.setStatus(hiringStatus);
			
			data.setStatusCode(hiringStatus.getStatusCode());
			applicant.setCreatedBy(principalService.getAuthPrincipal());
			
			final String id = JwtConfig.get();
			
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, id);
			applicant.setCandidate(candidate);
			
			data.setCandidateEmail(candidate.getUserEmail());
			final Applicant applicantId = applicantDao.save(applicant);

			final String applicantInsertAdminAPI = "http://localhost:8080/applicants";

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(JwtConfig.get());

			final RequestEntity<ApplicantInsertReqDto> applicantInsert = RequestEntity.post(applicantInsertAdminAPI)
					.headers(headers).body(data);

			final ResponseEntity<InsertResDto> responseAdmin = restTemplate.exchange(applicantInsert,
					InsertResDto.class);

			if (responseAdmin.getStatusCode().equals(HttpStatus.CREATED)) {
				insertRes.setId(applicantId.getId());
				insertRes.setMessage("Applicant Insert Success");
				em().getTransaction().commit();
			} else {
				em().getTransaction().rollback();
				throw new RuntimeException("Insert Failed");

			}

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertRes;
	}

}
