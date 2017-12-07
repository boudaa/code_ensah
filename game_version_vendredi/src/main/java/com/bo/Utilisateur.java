package com.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe persistante qui représente un objet metier qu'est l'utilisateur Elle
 * est annotée avec les annotation de JPA afin qu'elle soit gérée par le
 * Framework de persistance qu'est dans notre cas Hibernate
 * 
 * @author T.BOUDAA
 *
 */

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long idUtilisateur;

	private String nom;

	private String prenom;

	@Column(name = "login", unique = true)
	private String login;

	private String password;

	@Transient
	private double score;

	private double bestScore;

	@Transient
	private int compteurLancer;

	public Utilisateur() {
	}

	public Utilisateur(String nom, String prenom, String login, String password, double score, double bestScore,
			int compteurLancer) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.score = score;
		this.bestScore = bestScore;
		this.compteurLancer = compteurLancer;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", password=" + password + ", score=" + score + ", bestScore=" + bestScore
				+ ", compteurLancer=" + compteurLancer + "]";
	}

	public Utilisateur(String nom, String prenom, String login, String password, double score, double bestScore) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.score = score;
		this.bestScore = bestScore;
	}

	public Utilisateur(Long idUtilisateur, String nom, String prenom, String login, String password, double score,
			double bestScore) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.score = score;
		this.bestScore = bestScore;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {

		this.score = score;
	}

	public double getBestScore() {
		return bestScore;
	}

	public void setBestScore(double bestScore) {
		this.bestScore = bestScore;
	}

	public int getCompteurLancer() {
		return compteurLancer;
	}

	public void setCompteurLancer(int compteurLancer) {
		this.compteurLancer = compteurLancer;
	}

	public void incrementLance() {
		this.compteurLancer++;
	}

}
