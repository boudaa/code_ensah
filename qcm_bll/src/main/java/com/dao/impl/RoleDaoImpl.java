package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bo.security.Role;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;
import com.dao.RoleDao;
@Repository
public class RoleDaoImpl extends HibernateSpringGenericDaoImpl<Role, Long> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	public Role getRoleByName(String pRole) {
		
		List<Role> roles = getEntityByColValue("roleName",  pRole, "Role");
		return roles.get(0);
	}

}
