package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CompanyDao;
import com.lawencon.jobportaladmin.dao.EmploymentTypeDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.job.JobInsertReqDto;
import com.lawencon.jobportaladmin.model.Company;
import com.lawencon.jobportaladmin.model.EmploymentType;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Job;

public class JobService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private EmploymentTypeDao employmentTypeDao;
	
	public InsertResDto insertJob(JobInsertReqDto jobDto) {
		final Job job = new Job();
		
		InsertResDto result = null;
		
		try {
			em().getTransaction().begin();
			job.setJobName(null);
			
			final Company company = companyDao.getById(Company.class, jobDto.getCompanyId());
			job.setCompany(company);
			job.setStartDate(LocalDate.parse(jobDto.getStartDate()));
			job.setEndDate(LocalDate.parse(jobDto.getEndDate()));
			job.setDescription(jobDto.getDescription());
			job.setExpectedSalaryMin(Integer.valueOf(jobDto.getExpectedSalaryMin()));
			job.setExpectedSalaryMax(Integer.valueOf(jobDto.getExpectedSalaryMax()));
			
			final EmploymentType type = employmentTypeDao.getById(EmploymentType.class, jobDto.getEmploymentTypeId());
			job.setEmploymentType(type);
			
			final File file = new File();
			file.setFileName(jobDto.getFile());
			file.setFileExtension(jobDto.getFileExtension());
			file.setCreatedBy("ID Principal");
			//fileDao Needed
			
			jobDao.save(job);
			
			result = new InsertResDto();
			result.setId(null);
			result.setMessage("New job vacancy added!");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}
}
