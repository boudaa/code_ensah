package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.dao.utils.DbPropertiesLoader;
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
			// Charger les propriétés de la base de données à partir du fichier
			// de configuration de l'application
			dbProperties = DbPropertiesLoader.loadPoperties("Configuration.properties");
			// charger le pilote de la base de données
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = dbProperties.getProperty("db.url");
			String user = dbProperties.getProperty("db.user");
			String password = dbProperties.getProperty("db.password");
			connection = DriverManager.getConnection(dbUrl, user, password);

		} catch (SQLException e) {
			// Cas d'une erreur de connexion à la base alors logger l'erreur et
			// remonter l'exeception
			LOG.error("Error while trying to connect to database");
			throw new DataBaseException(e);

		} catch (ClassNotFoundException e) {
			// Cas d'une erreur dans le Driver
			LOG.error("Error while loading database driver");
			throw new DataBaseException(e);
		} catch (IOException e) {
			// Cas d'une erreur I/O alors logger l'erreur et remonter
			// l'exeception
			LOG.error("Error I/O while loading database parameters");
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
	public static Connection getConnexion() {

		return connection;
	}

}
