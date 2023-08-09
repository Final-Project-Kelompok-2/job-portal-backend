package com.lawencon.jobportaladmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dao.ProfileDao;
import com.lawencon.jobportaladmin.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private UserDao userDao;
	
	
}