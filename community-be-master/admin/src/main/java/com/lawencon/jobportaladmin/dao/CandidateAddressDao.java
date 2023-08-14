package com.lawencon.jobportaladmin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateAddress;
import com.lawencon.jobportaladmin.model.CandidateProfile;
import com.lawencon.jobportaladmin.model.CandidateUser;

@Repository
public class CandidateAddressDao extends AbstractJpaDao{
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public CandidateAddress getByCandidateId(String id){
		final StringBuilder sql = new StringBuilder(); 
				sql.append("SELECT  ");
				sql.append( "tca.id, ");
				sql.append ("address, ");
				sql.append ("residence_type, ");
				sql.append ("country, ");
				sql.append ("province, ");
				sql.append ("city, ");
				sql.append ("postal_code, ");
				sql.append ("user_id,");
				sql.append (" tcp.fullname ");
				sql.append ("FROM  ");
				sql.append ("	t_candidate_address tca  ");
				sql.append ("INNER JOIN  ");
				sql.append ("	t_candidate_user tcu  ");
				sql.append ("ON  ");
				sql.append ("	tcu.id = tca.user_id  ");
				sql.append ("INNER JOIN ");
				sql.append ("	t_candidate_profile tcp  ");
				sql.append ("ON ");
				sql.append ("	tcp.id = tcu.profile_id  ");
				sql.append ("WHERE  ");
				sql.append ("	tca.user_id  = :candidate");
		
		final Object candidateAddressObjs = em().createNativeQuery(sql.toString())
				.setParameter("candidate", id)
				.getSingleResult();
		
		final Object[] candidateAddressArr = (Object[]) candidateAddressObjs;

		CandidateAddress candidateAddress = null;
		if(candidateAddressArr.length > 0) {
			candidateAddress = new CandidateAddress();
			candidateAddress.setId(candidateAddressArr[0].toString());
			candidateAddress.setAddress(candidateAddressArr[1].toString());
			candidateAddress.setResidenceType(candidateAddressArr[2].toString());
			candidateAddress.setCountry(candidateAddressArr[3].toString());
			candidateAddress.setProvince(candidateAddressArr[4].toString());
			candidateAddress.setCity(candidateAddressArr[5].toString());
			candidateAddress.setPostalCode(candidateAddressArr[6].toString());
			
			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setFullname(candidateAddressArr[8].toString());
			
			final CandidateUser candidateUser = new CandidateUser();
			candidateUser.setId(candidateAddressArr[7].toString());
			candidateUser.setCandidateProfile(candidateProfile);
			candidateAddress.setCandidateUser(candidateUser);
		}
		
		
		return candidateAddress;

		
		
	}
	
	
	public List<CandidateAddress> testStringBuilder(String id){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("ca "); 
		sql.append("FROM "); 
		sql.append("CandidateAddress ca ");
		sql.append("INNER JOIN CandidateUser cu ");
		sql.append("WHERE ca.candidateUser = : id");
		
		final List<CandidateAddress> addressList = em().createQuery(sql.toString(),CandidateAddress.class)
				.setParameter("id", id)
				.getResultList();
		return addressList;
	}
	
	
}
