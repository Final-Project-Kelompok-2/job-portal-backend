package com.lawencon.jobportaladmin.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.constant.PersonTypes;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.CandidateProfileDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.EmployeeDao;
import com.lawencon.jobportaladmin.dao.HiredDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dao.PersonTypeDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.hired.HiredInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.CandidateProfile;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.Employee;
import com.lawencon.jobportaladmin.model.Hired;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.model.PersonType;
import com.lawencon.jobportaladmin.util.DateUtil;
import com.lawencon.jobportaladmin.util.GenerateCode;

@Service
public class HiredService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private HiredDao hiredDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CandidateUserDao candidateUserDao;

	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	
	@Autowired
	private PersonTypeDao personTypeDao;

	public InsertResDto insertHired(HiredInsertReqDto hiredData) {
		final InsertResDto resDto = new InsertResDto();

		try {
			em().getTransaction().begin();
			final Applicant applicant = applicantDao.getById(Applicant.class, hiredData.getApplicantId());

			Hired hired = new Hired();
			hired.setApplicant(applicant);
			hired.setStartDate(DateUtil.parseStringToLocalDateTime(hiredData.getStartDate()));

			if (hiredData.getStartDate() != null) {
				hired.setEndDate(DateUtil.parseStringToLocalDateTime(hiredData.getEndDate()));
			}

			hired = hiredDao.save(hired);

			final Employee employee = new Employee();
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class,applicant.getCandidate().getId());
			final Job job = jobDao.getById(Job.class, applicant.getJob().getId());
			employee.setCandidate(candidateUser);
			employee.setJob(job);
			employee.setEmployeeCode(GenerateCode.generateCode());
			employeeDao.save(employee);
			
			CandidateProfile candidateProfile = candidateProfileDao.getById(CandidateProfile.class, candidateUser.getCandidateProfile().getId());
			final PersonType personType = personTypeDao.getByCode(PersonTypes.EMPLOYEE.typeCode);
			candidateProfile.setPersonType(personType);
			
			candidateProfile = candidateProfileDao.saveAndFlush(candidateProfile);
			resDto.setId(hired.getId());
			resDto.setMessage("Insert Hired & Employee Success");
			
			em().getTransaction().commit();
			
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return resDto;
	}

}
