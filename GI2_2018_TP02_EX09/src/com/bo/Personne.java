package com.bo;

/**
 * Classe BO pour présenter une personne
 * 
 * @author boudaa
 *
 */
public class Personne {

	private String nom;
	private String prenom;

	public Personne() {
	}

	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return " " + "nom=" + nom + ", prenom=" + prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
