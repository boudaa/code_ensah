package com.dao.api;

import java.sql.Connection;

/**
 * Fabrique des dao et des services metiers
 * 
 * @author boudaa
 *
 */
public class DAOFactory {

	/** l'unique instance de cette classe * */
	private static DAOFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	private DAOFactory() {
		// interdir l'instantiation volentairement
	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static DAOFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new DAOFactory();
		}
		return staticInstance;
	}

	/**
	 * Permet de construire un DAO
	 * 
	 * @param pClasseName
	 *            : nom du DAO à construire
	 * @return
	 */
	public static IDao getDao(String pClasseName) {

		IDao dao = null;
		try {
			// On charge la classe par reflexion
			Class<?> c = Class.forName(pClasseName);
			// instantaion du DAO
			dao = (IDao) c.getConstructor(Connection.class).newInstance(JDBCConnection.getConnexion());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return dao;

	}

}
