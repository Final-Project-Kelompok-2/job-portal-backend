package com.lawencon.jobportaladmin.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao{

	private EntityManager em = ConnHandler.getManager();
	
	public Role getByCode(String code) {
		final String sql= "Select"
	}
	
}
