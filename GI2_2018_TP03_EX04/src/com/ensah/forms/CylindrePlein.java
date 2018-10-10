package com.ensah.forms;

public class CylindrePlein extends Forme {

	private double hauteur;

	private double rayon;

	public CylindrePlein(Point3D pCentre, double pDencite) {
		super(pCentre, pDencite);
	}

	public double calculerSurface() {

		return 2 * Math.PI * rayon * (rayon + hauteur);

	}

	public double calculerVolume() {

		return Math.PI * rayon * rayon * hauteur;

	}

}
