package com.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class DbPropertiesLoader {

	/**
	 * Permet de charger des propietees
	 * @return les proprietes 
	 * @throws IOException 
	 */
	public static Properties loadPoperties(String pName) throws IOException {
		InputStream propInputStream = null;
		propInputStream = DbPropertiesLoader.class
				.getResourceAsStream(pName);
		Properties properties = new Properties();
		properties.load(propInputStream);		
		return properties;
	}
	
}
