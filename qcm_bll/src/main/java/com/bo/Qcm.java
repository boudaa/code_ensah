package com.bo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.bo.security.Utilisateur;

public class Qcm {

	private Long idQcm;

	private double score;

	private Date dateRea;

	private Utilisateur utilisateur;

	private Set<Question> questions;

	public Long getIdQcm() {
		return idQcm;
	}

	public void setIdQcm(Long idQcm) {
		this.idQcm = idQcm;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getDateRea() {
		return dateRea;
	}

	public void setDateRea(Date dateRea) {
		this.dateRea = dateRea;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
