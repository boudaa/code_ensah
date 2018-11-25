package com.dao.api;


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
