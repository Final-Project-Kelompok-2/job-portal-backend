package com.lawencon.jobportaladmin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;

@Repository
public class AssignedJobQuestionDao extends AbstractJpaDao{

	public List<AssignedJobQuestionDao> getByJobId(String jobId){
		final String sql = "SELECT * FROM t_assigned_job_question tajq INNER JOIN t_question tq ON tq.id = tajq.question_id WHERE job_id = :jobId";
		
		
		
		
	}
	
}
