package com.lawencon.jobportalcandidate.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateAddress;

@Repository
public class CandidateAddressDao extends AbstractJpaDao{
	
	private EntityManager em = ConnHandler.getManager();
	
	public CandidateAddress getByCandidateId(String id){
		final String sql = "SELECT  "
				+ "	tca.id, "
				+ "	address, "
				+ "	residence_type, "
				+ "	country, "
				+ "	province, "
				+ "	city, "
				+ "	postal_code, "
				+ "	tcu.id, "
				+ "	tcp.fullname  "
				+ "FROM  "
				+ "	t_candidate_address tca  "
				+ "INNER JOIN  "
				+ "	t_candidate_user tcu  "
				+ "ON  "
				+ "	tcu.id = tca.user_id  "
				+ "INNER JOIN "
				+ "	t_candidate_profile tcp  "
				+ "ON "
				+ "	tcp.id = tcu.profile_id  "
				+ "WHERE  "
				+ "	tca.user_id  = :candidate";
		
		final Object candidateAddressObjs = this.em.createNativeQuery(sql)
				.setParameter("candidate", id)
				.getSingleResult();
		
		final Object[] candidateAddressArr = (Object[]) candidateAddressObjs;
		final CandidateAddress candidateAddressList = null;
	
		return candidateAddressList;
		
		
	}
	
}
