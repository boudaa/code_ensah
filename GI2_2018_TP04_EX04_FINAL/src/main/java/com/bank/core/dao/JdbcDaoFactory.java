package com.bank.core.dao;

import com.bank.core.dao.impl.jdbc.JdbcClientDAOImpl;
import com.bank.core.dao.impl.jdbc.JdbcCompteDAOImpl;
import com.dao.base.jdbc.JDBCConnection;

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

		JDBCConnection.createUniqueConnexion();

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

	public IClientDao getClientDao() {

		return new JdbcClientDAOImpl(JDBCConnection.getConnexion());
	}

	public ICompteDao getCompteDao() {
		return new JdbcCompteDAOImpl(JDBCConnection.getConnexion());
	}

}
