package com.dao.base;

import com.bank.core.config.ConfigurationLoader;
import com.dao.base.jdbc.JDBCTransactionManager;

/**
 * Permet de fabriquer un gestionnaire de transation il se base sur le fichier
 * de configuration pour décider quelle gestionnaire de transaction à créer
 * 
 * @author boudaa
 *
 */
public class TransactionManagerFactory {

	public static final String JDBC = "jdbc";
	public static final String ORM = "hibernate";

	private TransactionManagerFactory() {
	}

	public static final TransactionManager getTransactionManager() {

		// selon la configuration, nous allons choisir le bon gestionnaire de
		// transaction
		String lType = ConfigurationLoader.getProperties().getProperty("dao.type");

		if (JDBC.equals(lType)) {
			System.out.println("JDBC");
			return new JDBCTransactionManager();
		}
		if (ORM.equals(lType)) {
			System.out.println("Hibernate");

			// TODO
			// à implémenter
			return null;

		}

		// TODO : il faut declencher une exception : missing configuration
		return null;

	}

}
