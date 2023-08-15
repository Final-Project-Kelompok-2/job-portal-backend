package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.BenefitDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.HiringStatusDao;
import com.lawencon.jobportaladmin.dao.OfferingLetterDao;
import com.lawencon.jobportaladmin.dao.OwnedBenefitDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.offeringletter.OfferingLetterInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.Benefit;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.HiringStatus;
import com.lawencon.jobportaladmin.model.OfferingLetter;
import com.lawencon.jobportaladmin.model.OwnedBenefit;

@Service
public class OfferingLetterService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private OfferingLetterDao offeringLetterDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private HiringStatusDao hiringStatusDao;

	@Autowired
	private BenefitDao benefitDao;

	@Autowired
	private OwnedBenefitDao ownedBenefitDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CandidateUserDao candidateUserDao;

	public InsertResDto insertOfferingLetter(OfferingLetterInsertReqDto offeringData) {
		final InsertResDto resDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			OfferingLetter offeringLetter = new OfferingLetter();
			final Applicant applicant = applicantDao.getById(Applicant.class, offeringData.getApplicantId());
			offeringLetter.setAddress(offeringData.getAddress());
			offeringLetter.setSalary(offeringData.getSalary());
			offeringLetter.setApplicant(applicant);
			
			final HiringStatus hiringStatus = hiringStatusDao.getByCode(com.lawencon.jobportaladmin.constant.HiringStatus.OFFERING.statusCode);
			applicant.setStatus(hiringStatus);
			
			final List<OwnedBenefit> ownedBenefits = ownedBenefitDao.getByJob(applicant.getJob().getId());

			
			final CandidateUser candidate=  candidateUserDao.getById(CandidateUser.class, applicant.getCandidate().getId());
			
			offeringLetter = offeringLetterDao.save(offeringLetter);
			
			final String emailSubject = "Offering Letter";
			String emailBody = "Offering letter yang kami tawarkan yaitu anda "
					+ " akan bekerja di Kantor " + offeringLetter.getAddress() +" pada posisi " 
			+ applicant.getJob().getJobName() + " yaitu dengan gaji sebesar Rp ." + offeringData.getSalary() 
			+ " dengan benefit : ";
					
			
			if(ownedBenefits.size()>0) {
				for(int i=0;i<ownedBenefits.size();i++) {
					final Benefit benefit = benefitDao.getById(Benefit.class, ownedBenefits.get(i).getBenefit().getId());
					emailBody += benefit.getBenefitName();
				}
			}
			
			emailBody += " Semoga penawaran ini dapat menjadi pendukung dalam pekerjaan ini terima kasih";
			
			emailService.sendEmail(candidate.getUserEmail(), emailSubject, emailBody);
			
			resDto.setId(offeringLetter.getId());
			resDto.setMessage("Insert Offering Letter Success");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return resDto;
	}

}
