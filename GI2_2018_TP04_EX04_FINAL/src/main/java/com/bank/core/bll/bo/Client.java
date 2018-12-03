package com.bank.core.bll.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe qui encapsule les information d'un client
 * 
 * @author Tarik BOUDAA
 *
 */
@Entity
public class Client {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	/** code cin */
	private String cin;
	/** nom du client */
	private String nom;
	/** prenom du client */
	private String prenom;
	/** adresse du client */
	private String adresse;
	/** date naissance */
	private Date dateNaissance;

	/** Constructeur */
	public Client() {

	}

	/** Constructeur */
	public Client(String cni, String nom, String prenom, String adresse, Date dateNaissance) {

		this.cin = cni;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
	}

	/** redéfinition de la méthode toString */
	public String toString() {
		return "Personne [adresse=" + adresse + ", cni=" + cin + ", dateNaissance=" + dateNaissance + ", nom=" + nom
				+ ", prenom=" + prenom + "]";
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cni) {
		this.cin = cni;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
