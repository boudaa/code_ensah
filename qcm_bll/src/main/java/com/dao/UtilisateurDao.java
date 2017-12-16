package com.dao;

import com.bo.security.Utilisateur;
import com.genericdao.api.GenericDao;
public interface UtilisateurDao extends GenericDao<Utilisateur, Long> {

	public Utilisateur getUserByLogin(String pString);
}
