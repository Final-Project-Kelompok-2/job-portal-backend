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
import com.lawencon.jobportaladmin.dao.AssesmentDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.HiringStatusDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantUpdateReqDto;
import com.lawencon.jobportaladmin.dto.assesment.AssesmentInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.Assesment;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.HiringStatus;
import com.lawencon.jobportaladmin.util.DateUtil;

@Service
public class AssesmentService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private AssesmentDao assesmentDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private HiringStatusDao hiringStatusDao;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RestTemplate restTemplate;

	public InsertResDto insertAssesment(AssesmentInsertReqDto assesmentData) {
		final InsertResDto insertResDto = new InsertResDto();
		
		
		try {
			em().getTransaction().begin();
			Assesment assesment = new Assesment();
			assesment.setAssesmentDate(DateUtil.parseStringToLocalDateTime(assesmentData.getAssesmentDate()));
			assesment.setAssesmentLocation(assesmentData.getAssesmentLocation());
			Applicant applicant = applicantDao.getById(Applicant.class, assesmentData.getApplicantId());
			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, applicant.getCandidate().getId());
			assesmentData.setApplicantCode(applicant.getApplicantCode());
			assesment.setApplicant(applicant);
			
			assesment = assesmentDao.save(assesment);			
			
			final String emailSubject = "Assesment Schedule ";
			final String emailbody = "Selamat  " + candidate.getCandidateProfile().getFullname() + ", lamaran kamu telah "
					+ "di proses. Silahkan untuk datang ke "+ assesment.getAssesmentLocation() + " pada tanggal "+  
					assesmentData.getAssesmentDate() +" untuk Interview dengan user";
					
			emailService.sendEmail(candidate.getUserEmail(), emailSubject, emailbody);
			
			final HiringStatus hiringStatus = hiringStatusDao.getByCode(com.lawencon.jobportaladmin.constant.HiringStatus.ASSESMENT.statusCode);
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
				
				insertResDto.setId(assesment.getId());
				insertResDto.setMessage("Insert Assesment and Update Applicant Success");
				em().getTransaction().commit();
				
			} else {
				em().getTransaction().rollback();
				throw new RuntimeException("Insert Assesment and Update Applicant Failed");
			}
			
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		
		return insertResDto;
	}

}
