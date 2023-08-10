package com.lawencon.jobportaladmin.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Profile;
import com.lawencon.jobportaladmin.model.Role;
import com.lawencon.jobportaladmin.model.User;

@Repository
public class UserDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public User getByUsername(String email) {
		final String sql = "SELECT tu.id ,"
				+ " tu.user_password, "
				+ " tu.is_active, " 
				+ "	tu.profile_id, "
				+ " tp.full_name, "
				+ " tr.role_code,"
				+ " tp.photo_id "
				+ " FROM t_user tu"
				+ " INNER JOIN t_role tr ON tr.id = tu.role_id "
				+ " INNER JOIN t_profile tp ON tp.id = tu.profile_id " 
				+ " WHERE tu.user_email = :email ";

			final Object user = em().createNativeQuery(sql).setParameter("email", email).getSingleResult();

			final Object[] userArr = (Object[]) user;
			User userGet = null;

			if (userArr.length > 0) {
				userGet = new User();
				userGet.setId(userArr[0].toString());
				userGet.setUserPassword(userArr[1].toString());
				userGet.setIsActive(Boolean.valueOf(userArr[2].toString()));
				
				final Profile profile = new Profile();
				profile.setId(userArr[3].toString());
				profile.setFullName(userArr[4].toString());
				
				final File photo = new File();
				photo.setId(userArr[6].toString());
				
				profile.setPhoto(photo);
				userGet.setProfile(profile);
				
				final Role role = new Role();
				role.setRoleCode(userArr[5].toString());
				userGet.setRole(role);
				
				
			}
			
			return userGet;
	
	}
	
}
