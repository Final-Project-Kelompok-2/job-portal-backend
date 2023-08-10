package com.lawencon.jobportaladmin.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Company;
import com.lawencon.jobportaladmin.model.EmploymentType;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.model.User;

@Repository
public class JobDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<Job> getByCompany(String code) {
		final List<Job> jobs = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		final String sql = "SELECT "
				+ "	tj.id AS job_id,"
				+ "	job_name,"
				+ "	company_name,"
				+ "	address,"
				+ "	start_date,"
				+ "	end_date,"
				+ " hr_id,"
				+ " pic_id,"
				+ "	expected_salary_min,"
				+ "	expected_salary_max,"
				+ "	employment_type_name,"
				+ "	job_picture_id "
				+ "FROM "
				+ "	t_job tj "
				+ "INNER JOIN "
				+ "	t_company tc ON tc.id = tj.company_id "
				+ "INNER JOIN"
				+ "	t_employment_type tet ON tet.id = tj.employment_type_id "
				+ "INNER JOIN"
				+ "	t_user tu ON tu.id = tj.hr_id "
				+ "INNER JOIN"
				+ "	t_user tu2 ON tu2.id = tj.pic_id "
				+ "WHERE "
				+ "	company_code = :companycode";
			
		final List<?> jobObjs = em().createNativeQuery(sql)
				.setParameter("companycode", code)
				.getResultList();
		
		if (jobObjs.size() > 0) {
			for (Object jobObj : jobObjs) {
				final Object[] jobArr = (Object[]) jobObj;
				final Job job = new Job();
				job.setId(jobArr[0].toString());
				job.setJobName(jobArr[1].toString());
				
				final Company company = new Company();
				company.setCompanyName(jobArr[2].toString());
				company.setAddress(jobArr[3].toString());
				
				job.setStartDate(LocalDate.parse(jobArr[4].toString(), formatter));
				job.setEndDate(LocalDate.parse(jobArr[5].toString(), formatter));
				
				final User hr = new User();
				hr.setId(jobArr[6].toString());
				job.setHr(hr);
				
				final User pic = new User();
				pic.setId(jobArr[7].toString());
				job.setPic(pic);
				
				job.setExpectedSalaryMin(Integer.valueOf(jobArr[8].toString()));
				job.setExpectedSalaryMax(Integer.valueOf(jobArr[9].toString()));
				
				final EmploymentType type = new EmploymentType();
				type.setEmploymentTypeName(jobArr[10].toString());
				job.setEmploymentType(type);
				
				final File file = new File();
				file.setId(jobArr[11].toString());
				job.setJobPicture(file);
				
				jobs.add(job);
			}
		}
		
		return jobs;
	}

	public List<Job> getByAssignedHR(String id) {
		final List<Job> jobs = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		final String sql = "SELECT "
				+ "	tj.id AS job_id,"
				+ "	job_name,"
				+ "	company_name,"
				+ "	address,"
				+ "	start_date,"
				+ "	end_date,"
				+ " hr_id,"
				+ " pic_id,"
				+ "	expected_salary_min,"
				+ "	expected_salary_max,"
				+ "	employment_type_name,"
				+ "	job_picture_id "
				+ "FROM "
				+ "	t_job tj "
				+ "INNER JOIN "
				+ "	t_company tc ON tc.id = tj.company_id "
				+ "INNER JOIN"
				+ "	t_employment_type tet ON tet.id = tj.employment_type_id "
				+ "INNER JOIN"
				+ "	t_user tu ON tu.id = tj.hr_id "
				+ "INNER JOIN"
				+ "	t_user tu2 ON tu2.id = tj.pic_id "
				+ "WHERE "
				+ "	hr_id = :hrid";
			
		final List<?> jobObjs = em().createNativeQuery(sql)
				.setParameter("hrid", id)
				.getResultList();
		
		if (jobObjs.size() > 0) {
			for (Object jobObj : jobObjs) {
				final Object[] jobArr = (Object[]) jobObj;
				final Job job = new Job();
				job.setId(jobArr[0].toString());
				job.setJobName(jobArr[1].toString());
				
				final Company company = new Company();
				company.setCompanyName(jobArr[2].toString());
				company.setAddress(jobArr[3].toString());
				
				job.setStartDate(LocalDate.parse(jobArr[4].toString(), formatter));
				job.setEndDate(LocalDate.parse(jobArr[5].toString(), formatter));
				
				final User hr = new User();
				hr.setId(jobArr[6].toString());
				job.setHr(hr);
				
				final User pic = new User();
				pic.setId(jobArr[7].toString());
				job.setPic(pic);
				
				job.setExpectedSalaryMin(Integer.valueOf(jobArr[8].toString()));
				job.setExpectedSalaryMax(Integer.valueOf(jobArr[9].toString()));
				
				final EmploymentType type = new EmploymentType();
				type.setEmploymentTypeName(jobArr[10].toString());
				job.setEmploymentType(type);
				
				final File file = new File();
				file.setId(jobArr[11].toString());
				job.setJobPicture(file);
				
				jobs.add(job);
			}
		}
		
		return jobs;
	}
	
	public List<Job> getByAssignedPIC(String id) {
		final List<Job> jobs = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		final String sql = "SELECT "
				+ "	tj.id AS job_id,"
				+ "	job_name,"
				+ "	company_name,"
				+ "	address,"
				+ "	start_date,"
				+ "	end_date,"
				+ " hr_id,"
				+ " pic_id,"
				+ "	expected_salary_min,"
				+ "	expected_salary_max,"
				+ "	employment_type_name,"
				+ "	job_picture_id "
				+ "FROM "
				+ "	t_job tj "
				+ "INNER JOIN "
				+ "	t_company tc ON tc.id = tj.company_id "
				+ "INNER JOIN"
				+ "	t_employment_type tet ON tet.id = tj.employment_type_id "
				+ "INNER JOIN"
				+ "	t_user tu ON tu.id = tj.hr_id "
				+ "INNER JOIN"
				+ "	t_user tu2 ON tu2.id = tj.pic_id "
				+ "WHERE "
				+ "	pic_id = :picid";
			
		final List<?> jobObjs = em().createNativeQuery(sql)
				.setParameter("picid", id)
				.getResultList();
		
		if (jobObjs.size() > 0) {
			for (Object jobObj : jobObjs) {
				final Object[] jobArr = (Object[]) jobObj;
				final Job job = new Job();
				job.setId(jobArr[0].toString());
				job.setJobName(jobArr[1].toString());
				
				final Company company = new Company();
				company.setCompanyName(jobArr[2].toString());
				company.setAddress(jobArr[3].toString());
				
				job.setStartDate(LocalDate.parse(jobArr[4].toString(), formatter));
				job.setEndDate(LocalDate.parse(jobArr[5].toString(), formatter));
				
				final User hr = new User();
				hr.setId(jobArr[6].toString());
				job.setHr(hr);
				
				final User pic = new User();
				pic.setId(jobArr[7].toString());
				job.setPic(pic);
				
				job.setExpectedSalaryMin(Integer.valueOf(jobArr[8].toString()));
				job.setExpectedSalaryMax(Integer.valueOf(jobArr[9].toString()));
				
				final EmploymentType type = new EmploymentType();
				type.setEmploymentTypeName(jobArr[10].toString());
				job.setEmploymentType(type);
				
				final File file = new File();
				file.setId(jobArr[11].toString());
				job.setJobPicture(file);
				
				jobs.add(job);
			}
		}
		
		return jobs;
	}
}
