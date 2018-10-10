package com.test;

public class Factory {

	public static I getInstance(String className) {

		I i = null;
		try {

			
			//le nom de la classe qualifié (avec son nom d pakage) 
			Class c = Class.forName(className);

			i = (I) c.getConstructor().newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;

	}

}
