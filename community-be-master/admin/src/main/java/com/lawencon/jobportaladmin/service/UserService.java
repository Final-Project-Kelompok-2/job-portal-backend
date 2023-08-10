package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dao.ProfileDao;
import com.lawencon.jobportaladmin.dao.UserDao;
import com.lawencon.jobportaladmin.dto.login.LoginReqDto;
import com.lawencon.jobportaladmin.dto.login.LoginResDto;
import com.lawencon.jobportaladmin.model.User;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private UserDao userDao;
	
	
	public LoginResDto login(LoginReqDto loginData) {
		final User user = userDao.getByUsername(loginData.getUserEmail());
		final LoginResDto loginRes = new LoginResDto();
		
		if(!user.getIsActive()) {
			loginRes.setMessage("Akun anda nonaktif");
			return loginRes;
		}
		else {
			loginRes.setUserId(user.getId());
			loginRes.setFullName(user.getProfile().getFullName());
			loginRes.setProfileId(user.getProfile().getId());
			loginRes.setRoleCode(user.getRole().getRoleCode());
			loginRes.setPhotoId(user.getProfile().getPhoto().getId());
		}
		
		return loginRes;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByUsername(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email / Password salah");
	}


	
	
}