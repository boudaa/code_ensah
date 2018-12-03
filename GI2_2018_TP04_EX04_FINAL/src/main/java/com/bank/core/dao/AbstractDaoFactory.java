package com.bank.core.dao;

import org.apache.log4j.Logger;

import com.bank.core.config.ConfigurationLoader;

/**
 * Cette classe fabrique les fabriques des DAOs (Factory of Factories)
 * 
 * @author boudaa
 *
 */
public abstract class AbstractDaoFactory {

	private static final Logger LOGGER = Logger.getLogger(AbstractDaoFactory.class);

	public static final String JDBC = "jdbc";
	public static final String ORM = "hibernate";

	public abstract IClientDao getClientDao();

	public abstract ICompteDao getCompteDao();

	/**
	 * Méthode permettant de récupérer les Factory
	 * 
	 * @return
	 */
	public static AbstractDaoFactory getFactory() {

		// lire la configuration
		String daoType = ConfigurationLoader.getProperties().getProperty("dao.type");

		LOGGER.info("### L'application utilise une configuration de type " + daoType + "###");
		System.out.println("### L'application utilise une configuration de type " + daoType + "###");

		// selon la configuration, soit on va instancier la fabrique de type ORM ou de
		// type JDBC
		if (JDBC.equals(daoType)) {
			return new JdbcDaoFactory();
		}
		if (ORM.equals(daoType)) {

			return new HibernateDaoFactory();

		}

		throw new RuntimeException("mauvaise configuration de l'application");

	}
}