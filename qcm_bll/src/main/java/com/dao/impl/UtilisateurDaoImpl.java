package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bo.security.Utilisateur;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;
import com.dao.UtilisateurDao;
@Repository
public class UtilisateurDaoImpl extends HibernateSpringGenericDaoImpl<Utilisateur, Long> implements UtilisateurDao {

	
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public UtilisateurDaoImpl() {
		super(Utilisateur.class);
	}

	public Utilisateur getUserByLogin(String pLogin) {

		List users = hibernateTemplate.find(
				"from Utilisateur where login = ? ", pLogin);

		if (users == null || users.size() == 0 || users.size() != 1)
			throw new ObjectRetrievalFailureException(Utilisateur.class, pLogin);

		return (Utilisateur) users.get(0);
	}

}
