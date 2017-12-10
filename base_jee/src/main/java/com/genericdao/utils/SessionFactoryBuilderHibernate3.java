package com.genericdao.utils;

import javax.management.RuntimeErrorException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Permet de récupérer la fabrique des sessions
 * 
 * Cette classe est depricated dans la version 4 de Hibernate, pour la version 4
 * prière d'utiliser SessionFactoryBuilderHibernate4
 * 
 * @author T.BOUDAA
 *
 */
public class SessionFactoryBuilderHibernate3 {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {

			// Create the SessionFactory from hibernate.cfg.xml
			SessionFactory sf = new Configuration().configure().buildSessionFactory();

			return sf;

		} catch (RuntimeErrorException ex) {

			throw ex;
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
