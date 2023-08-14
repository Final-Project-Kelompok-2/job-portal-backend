package com.lawencon.jobportaladmin.service;

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
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.HiringStatusDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantUpdateReqDto;
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

	@Autowired
	private RestTemplate restTemplate;

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
			applicantRes.setCandidateName(applicantList.get(i).getCandidate().getCandidateProfile().getFullname());
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
			newApplicant.setAppliedDate(DateUtil.parseStringToLocalDateTime(applicantData.getAppliedDate()));

			final Job job = jobDao.getByCode(applicantData.getJobCode());
			newApplicant.setJob(job);

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
			e.printStackTrace();
		}

		return resDto;
	}

	public UpdateResDto updateApplicant(ApplicantUpdateReqDto updateData) {
		final UpdateResDto resDto = new UpdateResDto();

		try {
			em().getTransaction().begin();
			Applicant applicant = applicantDao.getById(Applicant.class, updateData.getApplicantId());
			updateData.setApplicantCode(applicant.getApplicantCode());

			final HiringStatus hiringStatus = hiringStatusDao.getById(HiringStatus.class, updateData.getStatusId());
			applicant.setStatus(hiringStatus);
			updateData.setStatusCode(hiringStatus.getStatusCode());
			
			applicant = applicantDao.saveAndFlush(applicant);
			
			final String updateApplicantAPI = "http://localhost:8081/applicants";
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(JwtConfig.get());
			 
			final RequestEntity<ApplicantUpdateReqDto> applicantUpdate = RequestEntity.patch(updateApplicantAPI).headers(headers).body(updateData);
			final ResponseEntity<UpdateResDto> responseCandidate = restTemplate.exchange(applicantUpdate, UpdateResDto.class);

			if (responseCandidate.getStatusCode().equals(HttpStatus.OK)) {

				resDto.setVersion(applicant.getVersion());
				resDto.setMessage("Update Application Success");
				em().getTransaction().commit();
				
			} else {
				
				em().getTransaction().rollback();
				throw new RuntimeException("Update Failed");
			}

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return resDto;
	}
}
