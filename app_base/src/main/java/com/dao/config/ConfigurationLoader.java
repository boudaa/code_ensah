package com.dao.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {

	private static Properties dbProperties;

	/**
	 * Permet de charger des propietees
	 * 
	 * @return les proprietes
	 * @throws IOException
	 */
	public static Properties getProperties(String pFileName) {

		try {
			if (dbProperties == null) {
				InputStream propInputStream = null;
				propInputStream = ConfigurationLoader.class.getResourceAsStream(pFileName);
				Properties properties = new Properties();
				properties.load(propInputStream);
				dbProperties = properties;
			}
		} catch (Exception e) {

			throw new ConfigurationException("le fichier de configuration ne peut pas etre chargé", e);

		}
		return dbProperties;
	}

}
