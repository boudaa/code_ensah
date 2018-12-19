package com.core.dao;

import java.util.Properties;

import com.core.dao.jdbc.StudentDaoImpl;
import com.dao.base.exceptions.DataAccessLayerException;
import com.dao.base.jdbc.JDBCConnection;
import com.dao.config.ConfigurationLoader;

/**
 * Fabrique des dao JDBC
 * 
 * @author boudaa
 *
 */
public class JdbcDaoFactory extends AbstractDaoFactory {

	/** l'unique instance de cette classe * */
	private static JdbcDaoFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	protected JdbcDaoFactory() throws DataAccessLayerException {

		Properties config = ConfigurationLoader.getProperties("/com/core/config/Configuration.properties");

		JDBCConnection.createUniqueConnexion(config.getProperty("dao.url"), config.getProperty("dao.user"),
				config.getProperty("dao.password"));

	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static AbstractDaoFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new JdbcDaoFactory();
		}
		return staticInstance;
	}

	public IStudentDao getStudentDao() {

		return new StudentDaoImpl(JDBCConnection.getConnexion());
	}

}
