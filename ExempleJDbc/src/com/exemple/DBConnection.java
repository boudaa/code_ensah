package com.exemple;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.exception.DataBaseException;

/**
 * Cette classe permet de faire une connexion à la base de données càd elle sert
 * à faire le pont entre l'application et la base de données. Cette classe est
 * implemente le pattern Singeton
 * 
 * @author Tarik BOUDAA
 */
public class DBConnection {

	/** Logger pour la journalisation */
	private final Logger LOG = Logger.getLogger(getClass());

	String user = "test";
	String password = "test";
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/tp4";

	/** Objet de connection */
	private static Connection connection;

	/**
	 * Constructeur privé pour interdir l'instanciation
	 * 
	 * @throws DataBaseException
	 */

	private DBConnection() throws DataBaseException {

		Properties dbProperties = null;

		try {

			// charger le pilote de la base de données
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			// Cas d'une erreur de connexion à la base alors logger l'erreur et
			// remonter l'exeception
			LOG.error("Error while trying to connect to database");
			throw new DataBaseException(e);

		} catch (ClassNotFoundException e) {
			// Cas d'une erreur dans le Driver
			LOG.error("Error while loading database driver");
			throw new DataBaseException(e);
		}

	}

	/**
	 * Méthode permet de retourner une instance de la connexion s'il existe et la
	 * créer sinon
	 * 
	 * @return connection
	 * @throws DataBaseException
	 */
	public static Connection createUniqueConnexion() throws DataBaseException {
		if (connection == null) {
			try {
				new DBConnection();
			} catch (DataBaseException e) {

				throw new DataBaseException(e);
			}
		}
		return connection;
	}

	/**
	 * Méthode permet de retourner une instance de la connexion s'il existe et la
	 * créer sinon
	 * 
	 * @return connection
	 * @throws DataBaseException
	 */
	public static Connection getConnexion() throws DataBaseException {

		if (connection != null) {

			return connection;

		}

		return createUniqueConnexion();
	}

}
