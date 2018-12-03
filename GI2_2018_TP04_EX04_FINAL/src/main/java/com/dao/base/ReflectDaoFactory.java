package com.dao.base;

import java.sql.Connection;

import com.dao.base.jdbc.JDBCConnection;

/**
 * Fabrique des daos en se basant sur le nom de leurs classe, elle s'appuit sur
 * Java Reflexion
 * 
 * @author boudaa
 *
 */
public class ReflectDaoFactory {

	/** l'unique instance de cette classe * */
	private static ReflectDaoFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	private ReflectDaoFactory() {
		// On interdit l'instantiation de cette classe de l'exterieur
	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static ReflectDaoFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new ReflectDaoFactory();
		}
		return staticInstance;
	}

	/**
	 * Permet de construire un DAO
	 * 
	 * @param pClasseName : nom du DAO à construire
	 * @return
	 */
	public IDao getDao(String pClasseName) {

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
