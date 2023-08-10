package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportalcandidate.dao.JobDao;
import com.lawencon.jobportalcandidate.dto.job.JobResDto;
import com.lawencon.jobportalcandidate.model.Job;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;
	
	public List<JobResDto> getJobs() {
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
	
	public List<JobResDto> getJobsByCompany(String code) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByCompany(code);
		
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
	
	public List<JobResDto> getJobsBySalary(Float salary) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getBySalary(salary);
		
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
