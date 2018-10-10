package com.dao;

import java.sql.Connection;

public class TransactionManagerFactory {

	private TransactionManagerFactory() {
	}

	public static final TransactionManager getTransactionManager(String pType) {

		if ("jdbc".equals(pType)) {
			return new JDBCTransactionManager();
		}

		if ("hibernate".equals(pType)) {
			// TODO
			return null;
		}

		// TODO
		return null;

	}

}
