package com.ensah.forms;

public class Brique extends Forme {

	private double hauteur;

	private double largeur;

	private double longueur;

	public Brique( Point3D centre, double h, double lr, double ln, double dencite) {

		// On appelle le constructeur de la classe de base pour initialiser les
		// attributs hérités
		super(centre, dencite);

		hauteur = h;
		largeur = lr;
		longueur = ln;
	}

	public String toString() {

		// On appelle la méthode de la classe de base
		String s = super.toString();

		// on fait une concaténation avec les information propre à Brique
		s = s + " Hauteur " + hauteur + " largeur : " + largeur + " longueur :" + longueur;

		return s;
	}

	/** implémentation de la méthode de calcul de surface */
	public double calculerSurface() {
		return (largeur * longueur) * 2 + (largeur * hauteur) * 2 + (hauteur * longueur) * 2;
	}

	/** implémentation de la méthode de calcul de volume */
	public double calculerVolume() {
		return (largeur * longueur) * hauteur;
	}

}
