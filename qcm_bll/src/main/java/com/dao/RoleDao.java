package com.dao;

import com.bo.security.Role;
import com.genericdao.api.GenericDao;
public interface RoleDao extends GenericDao<com.bo.security.Role, Long> {
	public Role getRoleByName(String pRole);
}
