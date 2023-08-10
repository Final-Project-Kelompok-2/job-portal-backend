package com.lawencon.jobportalcandidate.dao;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.Applicant;
import com.lawencon.jobportalcandidate.model.Company;
import com.lawencon.jobportalcandidate.model.HiringStatus;
import com.lawencon.jobportalcandidate.model.Job;

@Repository
public class ApplicantDao extends AbstractJpaDao{
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	public List<Applicant> getApplicantByCandidate(String id){
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		final String sql = "SELECT  "
				+ "	ta.id, "
				+ "	ta.applicant_code , "
				+ "	ta.applied_date , "
				+ "	ta.status_id , "
				+ "	ths.status_name , "
				+ "	ta.job_id, "
				+ "	tj.job_name, "
				+ "	tc.company_name "
				+ "FROM  "
				+ "	t_applicant ta  "
				+ "INNER JOIN  "
				+ "	t_job tj  "
				+ "ON "
				+ "	tj.id  = ta.job_id  "
				+ "INNER JOIN "
				+ "	t_hiring_status ths  "
				+ "ON  "
				+ "	ths.id  = ta.status_id  "
				+ "INNER JOIN  "
				+ "	t_company tc "
				+ "ON  "
				+ "	tc.id  = tj.company_id  "
				+ "WHERE ta.candidate_id  = :candidate";
		final List<?>applicantObjs = em().createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		final List<Applicant> applicantList = new ArrayList<>();
		if(applicantObjs.size() > 0) {
			for(Object applicantObj : applicantObjs) {
				final Object[] applicantArr = (Object[]) applicantObj;
				final Applicant applicant = new Applicant();
				
				applicant.setId(applicantArr[0].toString());
				applicant.setApplicantCode(applicantArr[1].toString());
				applicant.setAppliedDate(LocalDateTime.parse(applicantArr[2].toString(),formatter));
				 
				final HiringStatus hiringStatus = new HiringStatus();
				hiringStatus.setId(applicantArr[3].toString());
				hiringStatus.setStatusName(applicantArr[4].toString());
				applicant.setStatus(hiringStatus);
				
				final Company company = new Company();
				company.setCompanyName(applicantArr[7].toString());
				
				
				final Job job = new Job();
				job.setCompany(company);
				job.setId(applicantArr[5].toString());
				job.setJobName(applicantArr[6].toString());
				applicant.setJob(job);
				
				applicantList.add(applicant);
						
			}
		}
		return applicantList;
	}

	
}
