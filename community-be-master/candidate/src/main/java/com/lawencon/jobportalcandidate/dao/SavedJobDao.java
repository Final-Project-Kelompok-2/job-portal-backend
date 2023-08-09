package com.lawencon.jobportalcandidate.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.Company;
import com.lawencon.jobportalcandidate.model.EmploymentType;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.Job;
import com.lawencon.jobportalcandidate.model.SavedJob;

@Repository
public class SavedJobDao extends AbstractJpaDao {
	
	private EntityManager em = ConnHandler.getManager();

	public List<SavedJob> getByCandidate(String id) {
		final List<SavedJob> savedjobs = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		final String sql = "SELECT "
				+ "	tsj.id AS saved_id, "
				+ "	tj.id AS job_id, "
				+ "	job_name, "
				+ "	job_picture_id, "
				+ "	company_name, "
				+ "	tc.address, "
				+ "	start_date, "
				+ "	end_date, "
				+ "	expected_salary_min, "
				+ "	expected_salary_max, "
				+ "	employment_type_name, "
				+ "	user_id "
				+ "FROM "
				+ "	t_saved_job tsj "
				+ "INNER JOIN "
				+ "	t_job tj ON tj.id = tsj.job_id "
				+ "INNER JOIN "
				+ "	t_candidate_user tcu ON tcu.id = tsj.user_id "
				+ "INNER JOIN "
				+ "	t_company tc ON tc.id = tj.company_id "
				+ "INNER JOIN "
				+ "	t_employment_type tet ON tet.id = tj.employment_type_id "
				+ "WHERE "
				+ "	user_id = :candidate";
		
		final List<?> savedJobObjs = this.em.createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		
		if (savedJobObjs.size() > 0) {
			for (Object savedJobObj : savedJobObjs) {
				final Object[] savedjobArr = (Object[]) savedJobObj;
				final SavedJob savedjob = new SavedJob();
				savedjob.setId(savedjobArr[0].toString());
				
				final Job job = new Job();
				job.setId(savedjobArr[1].toString());
				job.setJobName(savedjobArr[2].toString());
				
				final File file = new File();
				file.setId(savedjobArr[3].toString());
				job.setJobPicture(file);
				
				final Company company = new Company();
				company.setCompanyName(savedjobArr[4].toString());
				company.setAddress(savedjobArr[5].toString());
				job.setCompany(company);
				
				job.setStartDate(LocalDate.parse(savedjobArr[6].toString(), formatter));
				job.setEndDate(LocalDate.parse(savedjobArr[7].toString(), formatter));
				job.setExpectedSalaryMin(Integer.valueOf(savedjobArr[8].toString()));
				job.setExpectedSalaryMax(Integer.valueOf(savedjobArr[9].toString()));
				
				final EmploymentType type = new EmploymentType();
				type.setEmploymentTypeName(savedjobArr[10].toString());
				job.setEmploymentType(type);
				savedjob.setJob(job);
				
				final CandidateUser candidate = new CandidateUser();
				candidate.setId(savedjobArr[11].toString());
				savedjob.setCandidateUser(candidate);

				savedjobs.add(savedjob);
			}
		}
		
		return savedjobs;
	}
}
