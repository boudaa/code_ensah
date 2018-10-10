package com.ensah;
/**
 * La classe Personne
 * 
 * @author BOUDAA
 *
 */
public class Personne {

	private String nom;

	private String prenom;

	private String tele;

	private String email;

	public Personne(String pNom, String pPrenom, String pTele, String pEmail) {

		nom = pNom;
		prenom = pPrenom;
		tele = pTele;
		email = pEmail;
	}

	public boolean equals(Object obj) {

		// Si les objets sont identiques
		if (this == obj)
			return true;

		// Puisque l'appelant ne peut pas etre null
		// alors si le paramètre est null alors false
		if (obj == null)
			return false;

		// Les objets doivent etre de meme classe
		if (getClass() != obj.getClass())
			return false;

		// Conversion de obj en Personne
		Personne lPersonne = (Personne) obj;

		// on doit s'assurer que email n'est pas null pour éviter une
		// NullPointerException, et on fait de meme pour toutes les chaines de
		// carcatères
		if (email == null) {
			if (lPersonne.email != null)
				return false;
		} else if (!email.equals(lPersonne.email))
			return false;
		if (nom == null) {
			if (lPersonne.nom != null)
				return false;
		} else if (!nom.equals(lPersonne.nom))
			return false;
		if (prenom == null) {
			if (lPersonne.prenom != null)
				return false;
		} else if (!prenom.equals(lPersonne.prenom))
			return false;
		if (tele == null) {
			if (lPersonne.tele != null)
				return false;
		} else if (!tele.equals(lPersonne.tele))
			return false;

		return true;
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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
