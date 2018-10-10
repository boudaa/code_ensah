package com.ensah.composantb;

import com.ensah.composanta.IMathCalcul;
import com.ensah.factories.Factory;

public class MathSommation {

	public static int max3Entier(int i, int j, int k) {
		IMathCalcul mathCalcul = Factory.getMathCalcul("com.ensah.composanta.MathCalcul");

		return mathCalcul.max2Entier(mathCalcul.max2Entier(i, j), k);
	}

	public static void main(String[] args) {

		System.out.println(MathSommation.max3Entier(11, 12, 13));
	}

}
