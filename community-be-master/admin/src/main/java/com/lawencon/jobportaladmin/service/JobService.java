package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.jobportaladmin.dao.AssignedJobQuestionDao;
import com.lawencon.jobportaladmin.dao.BenefitDao;
import com.lawencon.jobportaladmin.dao.CompanyDao;
import com.lawencon.jobportaladmin.dao.EmploymentTypeDao;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dao.OwnedBenefitDao;
import com.lawencon.jobportaladmin.dao.QuestionDao;
import com.lawencon.jobportaladmin.dao.UserDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.job.JobDetailResDto;
import com.lawencon.jobportaladmin.dto.job.JobInsertReqDto;
import com.lawencon.jobportaladmin.dto.job.JobResDto;
import com.lawencon.jobportaladmin.dto.job.JobUpdateReqDto;
import com.lawencon.jobportaladmin.model.AssignedJobQuestion;
import com.lawencon.jobportaladmin.model.Benefit;
import com.lawencon.jobportaladmin.model.Company;
import com.lawencon.jobportaladmin.model.EmploymentType;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.model.OwnedBenefit;
import com.lawencon.jobportaladmin.model.Question;
import com.lawencon.jobportaladmin.model.User;
import com.lawencon.jobportaladmin.util.DateUtil;
import com.lawencon.jobportaladmin.util.GenerateCode;
import com.lawencon.security.principal.PrincipalService;

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

	@Autowired
	private PrincipalService<String> principalService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BenefitDao benefitDao;

	@Autowired
	private OwnedBenefitDao ownedBenefitDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AssignedJobQuestionDao assignedJobQuestionDao;

	public List<JobResDto> getAllJobs() {
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
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMax().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());

			jobsDto.add(job);
		}

		return jobsDto;
	}

	public List<JobResDto> getByCompany(String code) {
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
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());

			jobsDto.add(job);
		}

		return jobsDto;
	}

	public List<JobResDto> getByPrincipal() {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByPerson(principalService.getAuthPrincipal());

		for (int i = 0; i < jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());
			jobsDto.add(job);
		}
		return jobsDto;
	}

	public InsertResDto insertJob(JobInsertReqDto jobDto) {

		InsertResDto result = new InsertResDto();

		try {
			em().getTransaction().begin();
			Job job = new Job();
			job.setJobName(jobDto.getJobName());

			final Company company = companyDao.getById(Company.class, jobDto.getCompanyId());
			job.setCompany(company);
			jobDto.setCompanyCode(company.getCompanyCode());

			job.setStartDate(DateUtil.parseStringToLocalDate(jobDto.getStartDate()));
			job.setEndDate(DateUtil.parseStringToLocalDate(jobDto.getEndDate()));
			job.setDescription(jobDto.getDescription());
			job.setJobCode(GenerateCode.generateCode());

			jobDto.setJobCode(job.getJobCode());

			final User hr = userDao.getById(User.class, jobDto.getHrId());
			final User pic = userDao.getById(User.class, jobDto.getPicId());
			job.setHr(hr);
			job.setPic(pic);
			job.setExpectedSalaryMin(jobDto.getExpectedSalaryMin());
			job.setExpectedSalaryMax(jobDto.getExpectedSalaryMax());

			final EmploymentType type = employmentTypeDao.getById(EmploymentType.class, jobDto.getEmploymentTypeId());
			job.setEmploymentType(type);
			jobDto.setEmploymentTypeCode(type.getEmploymentTypeCode());

			File file = new File();
			file.setFileName(jobDto.getFile());
			file.setFileExtension(jobDto.getFileExtension());
			file = fileDao.save(file);
			job.setJobPicture(file);
			job = jobDao.save(job);

			if (jobDto.getBenefits() != null) {
				for (int i = 0; i < jobDto.getBenefits().size(); i++) {

					OwnedBenefit ownedBenefit = new OwnedBenefit();
					final Benefit benefit = benefitDao.getById(Benefit.class,
							jobDto.getBenefits().get(i).getBenefitId());
					ownedBenefit.setBenefit(benefit);
					ownedBenefit.setJob(job);
					ownedBenefitDao.save(ownedBenefit);
				}
			}

			if (jobDto.getQuestions() != null) {
				for (int i = 0; i < jobDto.getQuestions().size(); i++) {

					AssignedJobQuestion assignQuestion = new AssignedJobQuestion();
					final Question question = questionDao.getById(Question.class,
							jobDto.getQuestions().get(i).getQuestionId());
					assignQuestion.setQuestion(question);
					assignQuestion.setJob(job);
					jobDto.getQuestions().get(i).setQuestionCode(question.getQuestionCode());
					assignedJobQuestionDao.save(assignQuestion);
				}
			}

			final String jobInsertCandidateAPI = "http://localhost:8081/jobs";

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(JwtConfig.get());

			final RequestEntity<JobInsertReqDto> jobInsert = RequestEntity.post(jobInsertCandidateAPI).headers(headers)
					.body(jobDto);

			final ResponseEntity<InsertResDto> responseCandidate = restTemplate.exchange(jobInsert, InsertResDto.class);

			if (responseCandidate.getStatusCode().equals(HttpStatus.CREATED)) {
				result.setId(job.getId());
				result.setMessage("New job vacancy added!");
				em().getTransaction().commit();
			} else {
				em().getTransaction().rollback();
				throw new RuntimeException("Insert Failed");

			}

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
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
				file.setCreatedBy(principalService.getAuthPrincipal());
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

		for (int i = 0; i < jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());

			jobsDto.add(job);
		}

		return jobsDto;
	}

	public List<JobResDto> getByPic(String id) {
		final List<JobResDto> jobsDto = new ArrayList<>();
		final List<Job> jobs = jobDao.getByAssignedPIC(id);

		for (int i = 0; i < jobs.size(); i++) {
			final JobResDto job = new JobResDto();
			job.setId(jobs.get(i).getId());
			job.setJobName(jobs.get(i).getJobName());
			job.setCompanyName(jobs.get(i).getCompany().getCompanyName());
			job.setAddress(jobs.get(i).getCompany().getAddress());
			job.setStartDate(jobs.get(i).getStartDate().toString());
			job.setEndDate(jobs.get(i).getEndDate().toString());
			job.setExpectedSalaryMin(jobs.get(i).getExpectedSalaryMin().toString());
			job.setExpectedSalaryMax(jobs.get(i).getExpectedSalaryMin().toString());
			job.setEmployementTypeName(jobs.get(i).getEmploymentType().getEmploymentTypeName());

			jobsDto.add(job);
		}

		return jobsDto;
	}

	public JobDetailResDto getDetail(String id) {
		final JobDetailResDto job = new JobDetailResDto();
		final Job jobDb = jobDao.getById(Job.class, id);
		
		job.setId(jobDb.getId());
		job.setJobName(jobDb.getJobName());
		job.setCompanyName(jobDb.getCompany().getCompanyName());
		job.setAddress(jobDb.getCompany().getAddress());
		job.setStartDate(jobDb.getStartDate().toString());
		job.setDescription(jobDb.getDescription());
		job.setEndDate(jobDb.getEndDate().toString());
		job.setExpectedSalaryMin(jobDb.getExpectedSalaryMin().toString());
		job.setExpectedSalaryMax(jobDb.getExpectedSalaryMin().toString());
		job.setEmployementTypeName(jobDb.getEmploymentType().getEmploymentTypeName());
		job.setFileId(jobDb.getJobPicture().getId());
		job.setCompanyPhotoId(jobDb.getCompany().getPhoto().getId());
		
		return job;
	}
	
}
