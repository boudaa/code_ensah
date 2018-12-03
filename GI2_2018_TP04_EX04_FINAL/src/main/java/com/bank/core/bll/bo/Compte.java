package com.bank.core.bll.bo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe BO pour présenter un compte bancaire
 * 
 * @author boudaa
 *
 */

@Entity
public class Compte {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Override
	public String toString() {
		return "Compte [num=" + id + ", titulaire=" + titulaire + ", solde=" + solde + ", decouvertMax=" + maxDecouvert
				+ ", debitMax=" + maxDebit + "]";
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="idPersonne")
	private Client titulaire;

	private double solde;

	private double maxDecouvert = 800;

	private double maxDebit = 3000;
	public Compte() {
	}

	public Compte(Long num, double solde, Client titulaire, double decouvertMax, double debitMax) {

		this.id = num;
		this.titulaire = titulaire;
		this.solde = solde;
		this.maxDecouvert = decouvertMax;
		this.maxDebit = debitMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Client titulaire) {
		this.titulaire = titulaire;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getMaxDecouvert() {
		return maxDecouvert;
	}

	public void setMaxDecouvert(double maxDecouvert) {
		this.maxDecouvert = maxDecouvert;
	}

	public double getMaxDebit() {
		return maxDebit;
	}

	public void setMaxDebit(double maxDebit) {
		this.maxDebit = maxDebit;
	}



}
