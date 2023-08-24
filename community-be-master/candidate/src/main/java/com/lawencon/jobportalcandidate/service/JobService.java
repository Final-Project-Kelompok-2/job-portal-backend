package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.AssignedJobQuestionDao;
import com.lawencon.jobportalcandidate.dao.CompanyDao;
import com.lawencon.jobportalcandidate.dao.EmploymentTypeDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.JobDao;
import com.lawencon.jobportalcandidate.dao.QuestionDao;
import com.lawencon.jobportalcandidate.dao.SavedJobDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.job.JobInsertReqDto;
import com.lawencon.jobportalcandidate.dto.job.JobResDto;
import com.lawencon.jobportalcandidate.model.AssignedJobQuestion;
import com.lawencon.jobportalcandidate.model.Company;
import com.lawencon.jobportalcandidate.model.EmploymentType;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.Job;
import com.lawencon.jobportalcandidate.model.Question;
import com.lawencon.jobportalcandidate.model.SavedJob;
import com.lawencon.jobportalcandidate.util.BigDecimalUtil;
import com.lawencon.jobportalcandidate.util.DateUtil;
import com.lawencon.security.principal.PrincipalService;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private EmploymentTypeDao employmentTypeDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AssignedJobQuestionDao assignedJobQuestionDao;
	
	@Autowired
	private SavedJobDao savedJobDao;
	
	@Autowired
	private PrincipalService<String> principalService;

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public List<JobResDto> getJobs() {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getAll(Job.class);

		for (int i = 0; i < jobs.size(); i++) {
			final JobResDto job = new JobResDto();	
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setDescription(jobs.get(i).getDescription());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMax().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());
			job.setFileId(jobs.get(i).getJobPicture().getId());
			job.setIsBookmark(savedJobDao.checkBookmark(jobs.get(i).getId(), principalService.getAuthPrincipal()));
			job.setCompanyPhotoId(jobs.get(i).getCompany().getPhoto().getId());
			jobsDto.add(job);
		}

		return jobsDto;
	}

	public List<JobResDto> getJobsByCompany(String code) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByCompany(code);

		for (int i = 0; i < jobs.size(); i++) {
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
			job.setCompanyPhotoId(jobs.get(i).getCompany().getPhoto().getId());
			jobsDto.add(job);
		}

		return jobsDto;
	}

	public List<JobResDto> getJobsBySalary(Float salary) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getBySalary(salary);

		for (int i = 0; i < jobs.size(); i++) {
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
			job.setCompanyPhotoId(jobs.get(i).getCompany().getPhoto().getId());
			jobsDto.add(job);
		}

		return jobsDto;
	}

	public InsertResDto insertJob(JobInsertReqDto job) {

		final InsertResDto insertResDto = new InsertResDto();

		try {
			em().getTransaction().begin();

			Job newJob = new Job();
			newJob.setJobName(job.getJobName());
			newJob.setJobCode(job.getJobCode());

			final Company company = companyDao.getByCode(job.getCompanyCode());
			newJob.setCompany(company);

			newJob.setStartDate(DateUtil.parseStringToLocalDate(job.getStartDate().toString()));
			newJob.setEndDate(DateUtil.parseStringToLocalDate(job.getEndDate().toString()));
			newJob.setDescription(job.getDescription());
			newJob.setExpectedSalaryMin(BigDecimalUtil.parseToBigDecimal(job.getExpectedSalaryMin().toString()));
			newJob.setExpectedSalaryMax(BigDecimalUtil.parseToBigDecimal(job.getExpectedSalaryMax().toString()));

			final EmploymentType employmentType = employmentTypeDao.getByCode(job.getEmploymentTypeCode());
			newJob.setEmploymentType(employmentType);

			File photo = new File();
			photo.setFileName(job.getFile());
			photo.setFileExtension(job.getFileExtension());

			photo = fileDao.save(photo);
			newJob.setJobPicture(photo);
			newJob = jobDao.save(newJob);

			if (job.getQuestions() != null) {

				for (int i = 0; i < job.getQuestions().size(); i++) {
					final Question question = questionDao.getByCode(job.getQuestions().get(i).getQuestionCode());
					AssignedJobQuestion assignQuestion = new AssignedJobQuestion();
					assignQuestion.setQuestion(question);
					assignQuestion.setJob(newJob);
					assignedJobQuestionDao.save(assignQuestion);
				}
			}

			insertResDto.setId(newJob.getId());
			insertResDto.setMessage("Insert job success");
			em().getTransaction().commit();

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertResDto;

	}

	public JobResDto getById(String jobId) {
		final Job job = jobDao.getById(Job.class, jobId);
		final JobResDto jobDto = new JobResDto();

		jobDto.setId(job.getId());
		jobDto.setJobName(job.getJobName());
		jobDto.setCompanyName(job.getCompany().getCompanyName());
		jobDto.setAddress(job.getCompany().getAddress());
		jobDto.setStartDate(job.getStartDate().toString());
		jobDto.setEndDate(job.getEndDate().toString());
		jobDto.setDescription(job.getDescription());
		jobDto.setExpectedSalaryMin(job.getExpectedSalaryMin().toString());
		jobDto.setExpectedSalaryMax(job.getExpectedSalaryMax().toString());
		jobDto.setEmployementTypeName(job.getEmploymentType().getEmploymentTypeName());
		jobDto.setFileId(job.getJobPicture().getId());
		jobDto.setCompanyPhotoId(job.getCompany().getPhoto().getId());
		return jobDto;

	}

}
