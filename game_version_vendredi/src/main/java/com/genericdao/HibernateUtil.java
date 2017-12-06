package com.genericdao;

import javax.management.RuntimeErrorException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Classe permettant de créer l'unique instance de la fabrique des sessions
 * Cette classe necessite Hibernate 4 pour la version 5 de Hibernate faut
 * consulter la doc.
 * 
 * @author T.BOUDAA
 *
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {
			// Create the SessionFactory from hibernate.cfg.xml

			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder ssrb = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.buildServiceRegistry());

			return sessionFactory;

		} catch (RuntimeErrorException ex) {

			throw ex;
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
