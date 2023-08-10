package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CompanyDao;
import com.lawencon.jobportaladmin.dao.EmploymentTypeDao;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.job.JobInsertReqDto;
import com.lawencon.jobportaladmin.dto.job.JobResDto;
import com.lawencon.jobportaladmin.dto.job.JobUpdateReqDto;
import com.lawencon.jobportaladmin.model.Company;
import com.lawencon.jobportaladmin.model.EmploymentType;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Job;

@Service
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

	@Autowired
	private FileDao fileDao;
	
	public List<JobResDto> getAllJobs() {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getAll(Job.class);
		
		for (int i=0; i<jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setDescription(jobs.get(i).getDescription());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());
			job.setFileId(jobs.get(i).getJobPicture().getId());
			
			jobsDto.add(job);
		}
		
		return jobsDto;
	}

	public InsertResDto insertJob(JobInsertReqDto jobDto) {
		final Job job = new Job();

		InsertResDto result = null;

		try {
			em().getTransaction().begin();
			job.setJobName(jobDto.getJobName());

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
			fileDao.save(file);
			job.setJobPicture(file);
			job.setCreatedBy("ID Principal");
			jobDao.save(job);

			result = new InsertResDto();
			result.setId(job.getId());
			result.setMessage("New job vacancy added!");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return result;
	}

	public UpdateResDto updateJob(JobUpdateReqDto jobDto) {
		final Job job = jobDao.getById(Job.class, jobDto.getId());

		UpdateResDto result = null;

		try {
			em().getTransaction().begin();
			job.setJobName(jobDto.getJobName());

			final Company company = companyDao.getById(Company.class, jobDto.getCompanyId());
			job.setCompany(company);
			job.setStartDate(LocalDate.parse(jobDto.getStartDate()));
			job.setEndDate(LocalDate.parse(jobDto.getEndDate()));
			job.setDescription(jobDto.getDescription());
			job.setExpectedSalaryMin(Integer.valueOf(jobDto.getExpectedSalaryMin()));
			job.setExpectedSalaryMax(Integer.valueOf(jobDto.getExpectedSalaryMax()));

			final EmploymentType type = employmentTypeDao.getById(EmploymentType.class, jobDto.getEmploymentTypeId());
			job.setEmploymentType(type);

			if (jobDto.getFile() != null) {				
				final File file = new File();
				file.setFileName(jobDto.getFile());
				file.setFileExtension(jobDto.getFileExtension());
				file.setCreatedBy("ID Principal");
				fileDao.save(file);
				job.setJobPicture(file);
				fileDao.deleteById(File.class, jobDto.getFileId());
			}
			
			jobDao.saveAndFlush(job);
			
			result = new UpdateResDto();
			result = new UpdateResDto();
			result.setVersion(job.getVersion());
			result.setMessage("New job vacancy added!");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}
		
		return result;
	}

	public List<JobResDto> getByHr(String id) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByAssignedHR(id);
		
		for (int i=0; i<jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setDescription(jobs.get(i).getDescription());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());
			job.setFileId(jobs.get(i).getJobPicture().getId());
			
			jobsDto.add(job);
		}
		
		return jobsDto;
	}
	
	public List<JobResDto> getByPic(String id) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByAssignedPIC(id);
		
		for (int i=0; i<jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setDescription(jobs.get(i).getDescription());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());
			job.setFileId(jobs.get(i).getJobPicture().getId());
			
			jobsDto.add(job);
		}
		
		return jobsDto;
	}
}
