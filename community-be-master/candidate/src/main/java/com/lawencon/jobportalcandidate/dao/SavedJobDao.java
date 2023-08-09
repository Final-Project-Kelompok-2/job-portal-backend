package com.lawencon.jobportalcandidate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.Job;
import com.lawencon.jobportalcandidate.model.SavedJob;

@Repository
public class SavedJobDao extends AbstractJpaDao {
	
	private EntityManager em = ConnHandler.getManager();

	public SavedJob insert(SavedJob savedjob) {
		em.persist(savedjob);
		return savedjob;
	}
	
	public List<SavedJob> getByCandidate(String id) {
		List<SavedJob> savedjobs = new ArrayList<>();
		
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

				savedjobs.add(savedjob);
			}
		}
		
		return savedjobs;
	}
}
