package com.dao;

import java.sql.Connection;


public class DaoFactory {

	/** l'unique instance de cette classe * */
	private static DaoFactory staticInstance = null;

	private DaoFactory() {

	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static DaoFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new DaoFactory();
		}
		return staticInstance;
	}

	public static Dao getDao(String className) {

		Dao dao = null;
		try {
			Class<?> c = Class.forName(className);
			dao = (Dao) c.getConstructor(Connection.class).newInstance(DBConnection.getConnexion());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return dao;

	}



}
