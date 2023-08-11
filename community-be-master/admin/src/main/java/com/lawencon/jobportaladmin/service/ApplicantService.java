package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantResDto;
import com.lawencon.jobportaladmin.model.Applicant;


@Service
public class ApplicantService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private ApplicantDao applicantDao;

	
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
}
