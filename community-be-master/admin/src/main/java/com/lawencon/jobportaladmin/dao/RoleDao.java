package com.lawencon.jobportaladmin.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao{

	private EntityManager em = ConnHandler.getManager();
	
	public List<Role> getByCode() {
		final String sql= "SELECT tr.id, tr.role_name FROM t_role tr "
				+ " WHERE tr.role_code != 'ADM' ";
				
		final List<Role> roles = em.createNativeQuery(sql,Role.class).getResultList();
		return roles;
		
	}
	
}
