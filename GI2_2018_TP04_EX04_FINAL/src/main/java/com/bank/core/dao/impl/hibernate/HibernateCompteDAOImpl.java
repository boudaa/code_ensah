package com.bank.core.dao.impl.hibernate;

import com.bank.core.bll.bo.Compte;
import com.bank.core.dao.ICompteDao;
import com.dao.base.hibernate.HibernateGenericDaoImpl;

/**
 * Classe qui offre les services de gestion des comptes dans la base donnees
 * 
 * @author Tarik BOUDAA
 * 
 */

/**
 * Classe d'implémentation d'un DAO pour Client
 * 
 * @author Tarik BOUDAA
 * 
 */
public class HibernateCompteDAOImpl extends HibernateGenericDaoImpl<Compte, Long> implements ICompteDao {

	public HibernateCompteDAOImpl() {
		super(Compte.class);
	}

}
