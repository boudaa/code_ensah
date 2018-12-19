package com.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Permet de récupérer la fabrique des sessions pour Hibernate version >= 4
 * 
 * est donc necessaire
 * 
 * @author T.BOUDAA
 *
 */
public class SessionFactoryBuilder {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();

		}

		return sessionFactory;
	}

}
