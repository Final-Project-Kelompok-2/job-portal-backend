package com.lawencon.jobportaladmin.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateWorkExp;

@Repository
public class CandidateWorkExpDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<CandidateWorkExp> getByCandidate(String id) {
		final List<CandidateWorkExp> works = new ArrayList<>();
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		final String sql = "SELECT"
				+ "	tcwe.id AS work_id,"
				+ "	position_name,"
				+ "	company_name,"
				+ "	address,"
				+ "	responsibility,"
				+ "	reason_leave,"
				+ "	last_salary,"
				+ "	start_date,"
				+ "	end_date"
				+ "FROM "
				+ "	t_candidate_work_exp tcwe"
				+ "WHERE "
				+ "	user_id = :candidate";
		
		final List<?> workObjs = em().createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		
		if (workObjs.size() > 0) {
			for (Object workObj : workObjs) {
				final Object[] workArr = (Object[]) workObj;
				final CandidateWorkExp work = new CandidateWorkExp();
				work.setId(workArr[0].toString());
				work.setPositionName(workArr[1].toString());
				work.setCompanyName(workArr[2].toString());
				work.setAddress(workArr[3].toString());
				work.setResponsibility(workArr[4].toString());
				work.setReasonLeave(workArr[5].toString());
				work.setLastSalary(Float.valueOf(workArr[6].toString()));
				work.setStartDate(LocalDateTime.parse(workArr[7].toString(), formatter));
				work.setEndDate(LocalDateTime.parse(workArr[8].toString(), formatter));
			}
		}
		
		return works;
	}
	
	public List<CandidateWorkExp> getByCandidateEmail(String email){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cwe ");
		sql.append("FROM CandidateWorkExp cwe ");
		sql.append("INNER JOIN CandidateUser cu ");
		sql.append("WHERE cu.userEmail = :email");
		final List<CandidateWorkExp>workExpList = em().createQuery(sql.toString(), CandidateWorkExp.class)
				.setParameter("email", email)
				.getResultList();
		return workExpList;
	}
	
}
