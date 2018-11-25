package com.dao.api;

import java.sql.Connection;
import java.sql.SQLException;

import com.exception.DataBaseException;

public class JDBCTransactionManager implements TransactionManager {

	Connection connexion = null;

	public JDBCTransactionManager() {
		connexion = JDBCConnection.getConnexion();

	}

	public void commit() throws DataBaseException {
		try {
			connexion.commit();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}

	}

	public void rollback() throws DataBaseException {
		try {
			connexion.rollback();
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
	}

	public void beginTransaction() throws DataBaseException {

		try {
			connexion.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
	}

	public void setAutoCommitTrue() throws DataBaseException {

		try {
			connexion.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
	}

}
