package com.ensah.factories;

import com.ensah.composanta.IMathCalcul;
import com.ensah.composanta.MathCalcul;

public class Factory {

	public static IMathCalcul getMathCalcul(String className) {

		IMathCalcul math = null;
		try {

			Class c = Class.forName(className);

			math = (IMathCalcul) c.getConstructor().newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return math;

	}

}
