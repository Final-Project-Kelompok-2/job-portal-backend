package com.lawencon.jobportalcandidate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateAddress;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Repository
public class CandidateAddressDao extends AbstractJpaDao {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public List<CandidateAddress> getByCandidateId(String id) {
		final String sql = "SELECT  " + "	tca.id, " + "	address, " + "	residence_type, " + "	country, "
				+ "	province, " + "	city, " + "	postal_code, " + "	user_id," + " tcp.fullname " + "FROM  "
				+ "	t_candidate_address tca  " + "INNER JOIN  " + "	t_candidate_user tcu  " + "ON  "
				+ "	tcu.id = tca.user_id  " + "INNER JOIN " + "	t_candidate_profile tcp  " + "ON "
				+ "	tcp.id = tcu.profile_id  " + "WHERE  " + "	tca.user_id  = :candidate";

		final List<?> candidateAddressObjs = em().createNativeQuery(sql)

				.setParameter("candidate", id).getResultList();
		final List<CandidateAddress> candidateAddressList = new ArrayList<>();

		if (candidateAddressObjs.size() > 0) {
			for (Object candidateAddressObj : candidateAddressObjs) {

				final Object[] candidateAddressArr = (Object[]) candidateAddressObj;
				final CandidateAddress candidateAddress = new CandidateAddress();
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
				candidateAddressList.add(candidateAddress);
			}
		}

		return candidateAddressList;

	}

}
