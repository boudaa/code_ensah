package com.bo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.bo.security.Utilisateur;


@Entity
public class Qcm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idQcm;

	private double score;

	private Date dateRea;

	@ManyToOne
    @JoinColumn(name="utilisateur_id")
	private Utilisateur utilisateur;

	@Transient
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
