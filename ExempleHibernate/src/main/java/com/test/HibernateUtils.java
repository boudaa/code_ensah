package com.test;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sf = null;

	public synchronized static SessionFactory getSF() {

		if (sf == null) {
			
			
			//Create session factory see docuementation 
			sf = new Configuration().configure()

					.buildSessionFactory();
		}

		return sf;

	}

}
