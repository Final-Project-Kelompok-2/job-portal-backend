package com.lawencon.jobportaladmin.service;

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
import com.lawencon.jobportaladmin.dao.InterviewDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantUpdateReqDto;
import com.lawencon.jobportaladmin.dto.interview.InterviewInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.HiringStatus;
import com.lawencon.jobportaladmin.model.Interview;
import com.lawencon.jobportaladmin.util.DateUtil;

@Service
public class InterviewService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private InterviewDao interviewDao;
	
	@Autowired
	private ApplicantDao applicantDao;
	
	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private HiringStatusDao hiringStatusDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public InsertResDto insertInterview(InterviewInsertReqDto interviewData) {
		final InsertResDto resDto = new InsertResDto();
				
		try {
			em().getTransaction().begin();
			Interview interview = new Interview();
			Applicant applicant = applicantDao.getById(Applicant.class, interviewData.getApplicantId());
			
			interview.setApplicant(applicant);
			interview.setInterviewDate(DateUtil.parseStringToLocalDateTime(interviewData.getInterviewDate()));
			interview.setInterviewLocation(interviewData.getInterviewLocation());
			
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, applicant.getCandidate().getId());
			
			
			interview = interviewDao.save(interview);			
			
			final String emailSubject = "Interview Schedule ";
			final String emailbody = "Selamat " + candidate.getCandidateProfile().getFullname() + ", kamu telah masuk ke tahap Interview User. "
					+ " Silahkan mengikuti jadwal interview pada tanggal "+ interview.getInterviewDate() + " di " + interview.getInterviewLocation(); 
					
			emailService.sendEmail(candidate.getUserEmail(), emailSubject, emailbody);
			
			final HiringStatus hiringStatus = hiringStatusDao.getByCode(com.lawencon.jobportaladmin.constant.HiringStatus.INTERVIEWUSER.statusCode);
			applicant.setStatus(hiringStatus);
			
			applicant = applicantDao.saveAndFlush(applicant);
			
			final String updateApplicantAPI = "http://localhost:8081/applicants";
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(JwtConfig.get());
			
			final ApplicantUpdateReqDto updateDto = new ApplicantUpdateReqDto();
			updateDto.setApplicantCode(applicant.getApplicantCode());
			updateDto.setStatusCode(hiringStatus.getStatusCode());
			
			final RequestEntity<ApplicantUpdateReqDto> applicantUpdate = RequestEntity.patch(updateApplicantAPI).headers(headers).body(updateDto);
			final ResponseEntity<UpdateResDto> responseCandidate = restTemplate.exchange(applicantUpdate, UpdateResDto.class);

			if (responseCandidate.getStatusCode().equals(HttpStatus.OK)) {
				
				resDto.setId(interview.getId());
				resDto.setMessage("Insert Interview and Update Applicant Success");
				em().getTransaction().commit();
				
			} else {
				em().getTransaction().rollback();
				throw new RuntimeException("Insert Interview and Update Applicant Failed");
			}
			
			
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return resDto;
	}
	
	
}