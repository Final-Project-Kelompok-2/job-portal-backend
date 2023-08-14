package com.lawencon.jobportaladmin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateSkill;

@Repository
public class CandidateSkillDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<CandidateSkill> getByCandidate(String id) {
		final List<CandidateSkill> skills = new ArrayList<>();
		
		final String sql = "SELECT "
				+ "	tcs.id AS skill_id, "
				+ "	skill_name "
				+ "FROM "
				+ "	t_candidate_skill tcs "
				+ "WHERE "
				+ "	user_id = :candidate";
		final List<?> skillObjs = em().createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		
		if (skillObjs.size() > 0) {
			for (Object skillObj : skillObjs) {
				final Object[] skillArr = (Object[]) skillObj;
				final CandidateSkill skill = new CandidateSkill();
				skill.setId(skillArr[0].toString());
				skill.setSkillName(skillArr[1].toString());
				
				skills.add(skill);
			}
		}
		
		return skills;
	}
	
	public List<CandidateSkill> getByCandidateEmail(String email){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cs ");
		sql.append("FROM CandidateSkill cs ");
		sql.append("INNER JOIN CandidateUser cu");
		sql.append("WHERE cu.userEmail = :email");
		
		final List<CandidateSkill>skillList = em().createQuery(sql.toString(),CandidateSkill.class)
				.setParameter("email", email)
				.getResultList();
		return skillList;
	}
	
}
