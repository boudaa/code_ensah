package com.dao.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.dao.base.exceptions.DataAccessLayerException;

/**
 * Cette classe permet de faire une connexion à la base de données céd elle sert
 * à faire le pont entre l'application et la base de données. Cette classe est
 * implemente le pattern Singeton
 * 
 * @author Tarik BOUDAA
 */
public class JDBCConnection {

	/** Logger pour la journalisation */
	private final Logger LOG = Logger.getLogger(getClass());

	/** Objet de connection */
	private static Connection connection;

	/**
	 * Constructeur privé pour interdir l'instanciation
	 * 
	 * @throws DataAccessLayerException
	 */

	private JDBCConnection(String pUrl, String pUser, String pPsswd) throws DataAccessLayerException {

		try {
			// Charger les propriétés de la base de données à partir du fichier
			// de configuration de l'application
			// charger le pilote de la base de données
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(pUrl, pUser, pPsswd);

		} catch (SQLException e) {
			// Cas d'une erreur de connexion à la base alors logger l'erreur et
			// remonter l'exeception
			LOG.error("Error while trying to connect to database");
			throw new DataAccessLayerException(e);

		} catch (ClassNotFoundException e) {
			// Cas d'une erreur dans le Driver
			LOG.error("Error while loading database driver");
			throw new DataAccessLayerException(e);
		}

	}

	/**
	 * Méthode permet de retourner une instance de la connexion s'il existe et la
	 * créer sinon
	 * 
	 * @return connection
	 * @throws DataAccessLayerException
	 */
	public static Connection createUniqueConnexion(String pUrl, String pUser, String pPsswd)
			throws DataAccessLayerException {
		if (connection == null) {
			try {
				new JDBCConnection(pUrl, pUser, pPsswd);
			} catch (DataAccessLayerException e) {

				throw new DataAccessLayerException(e);
			}
		}
		return connection;
	}

	/**
	 * Méthode permet de retourner une instance de la connexion s'il existe et la
	 * créer sinon
	 * 
	 * @return connection
	 * @throws DataAccessLayerException
	 */
	public static Connection getConnexion() {

		return connection;
	}

}
