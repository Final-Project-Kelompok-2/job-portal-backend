package com.lawencon.jobportalcandidate.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;

@Repository
public class CandidateUserDao extends AbstractJpaDao{
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public CandidateUser getByUsername(String email) {
		final String sql = "SELECT tu.id ,"
				+ " tu.user_password, "
				+ " tu.is_active, " 
				+ "	tu.profile_id, "
				+ " tp.fullname, "
				+ " tp.file_id "
				+ " FROM t_candidate_user tu"
				+ " INNER JOIN t_candidate_profile tp ON tp.id = tu.profile_id " 
				+ " WHERE tu.user_email = :email ";

			final Object user = em().createNativeQuery(sql).setParameter("email", email).getSingleResult();

			final Object[] userArr = (Object[]) user;
			CandidateUser userGet = null;

			if (userArr.length > 0) {
				userGet = new CandidateUser();
				userGet.setId(userArr[0].toString());
				userGet.setUserPassword(userArr[1].toString());
				userGet.setIsActive(Boolean.valueOf(userArr[2].toString()));
				
				final CandidateProfile profile = new CandidateProfile();
				profile.setId(userArr[3].toString());
				profile.setFullname(userArr[4].toString());
				
				final File photo = new File();
				photo.setId(userArr[5].toString());
				
				profile.setFile(photo);
				userGet.setCandidateProfile(profile);
			}
			
			return userGet;
	}
	
}
