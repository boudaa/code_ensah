package com.bo;

/**
 * Classe BO pour présenter un compte bancaire
 * 
 * @author boudaa
 *
 */
public class Compte {

	private Long num;

	@Override
	public String toString() {
		return "Compte [num=" + num + ", titulaire=" + titulaire + ", solde=" + solde + ", decouvertMax=" + decouvertMax
				+ ", debitMax=" + debitMax + "]";
	}

	private Client titulaire;

	private double solde;

	private double decouvertMax = 800;

	private double debitMax = 3000;

	public Compte() {
	}

	public Compte(Long num,  double solde, Client titulaire, double decouvertMax, double debitMax) {
	
		this.num = num;
		this.titulaire = titulaire;
		this.solde = solde;
		this.decouvertMax = decouvertMax;
		this.debitMax = debitMax;
	}



	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public double getDecouvertMax() {
		return decouvertMax;
	}

	public void setDecouvertMax(double decouvertMax) {
		this.decouvertMax = decouvertMax;
	}

	public double getDebitMax() {
		return debitMax;
	}

	public void setDebitMax(double debitMax) {
		this.debitMax = debitMax;
	}

}
