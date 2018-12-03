package com.bank.core.dao;

import com.bank.core.dao.impl.hibernate.HibernateClientDAOImpl;
import com.bank.core.dao.impl.hibernate.HibernateCompteDAOImpl;

/**
 * Fabrique des dao hibernate
 * 
 * @author boudaa
 *
 */
public class HibernateDaoFactory extends AbstractDaoFactory {

	/** l'unique instance de cette classe * */
	private static HibernateDaoFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	protected HibernateDaoFactory() {
		// interdir l'instantiation volentairement
	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static AbstractDaoFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new HibernateDaoFactory();
		}
		return staticInstance;
	}

	public IClientDao getClientDao() {
		return new HibernateClientDAOImpl();
	}

	
	public ICompteDao getCompteDao() {
		return new HibernateCompteDAOImpl();
	}



}
