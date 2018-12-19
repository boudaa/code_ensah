package com.dao.base.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.dao.base.TransactionManager;
import com.dao.base.exceptions.DataAccessLayerException;

/**
 * Classe qui implémente un gestionnaire de transaction avec JDBC
 * 
 * @author boudaa
 *
 */
public class JDBCTransactionManager implements TransactionManager {

	Connection connexion = null;

	public JDBCTransactionManager() {
		connexion = JDBCConnection.getConnexion();

	}

	public void commit() throws DataAccessLayerException {
		try {
			connexion.commit();
		} catch (SQLException e) {
			throw new DataAccessLayerException(e);
		}

	}

	public void rollback() throws DataAccessLayerException {
		try {
			connexion.rollback();
		} catch (SQLException e) {
			throw new DataAccessLayerException(e);
		}
	}

	public void beginTransaction() throws DataAccessLayerException {

		try {
			connexion.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessLayerException(e);
		}
	}

	public void setAutoCommitTrue() throws DataAccessLayerException {

		try {
			connexion.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DataAccessLayerException(e);
		}
	}

}
