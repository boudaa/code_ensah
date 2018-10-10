package com.bo;

/**
 * Classe BO pour présenter un compte bancaire
 * 
 * @author boudaa
 *
 */
public class Compte {

	private int num;

	private Personne titulaire;

	private double solde;

	private double decouvertMax = 800;

	private double debitMax = 3000;

	@Override
	public String toString() {
		return "Compte [num=" + num + ", titulaire=" + titulaire + ", solde=" + solde + ", decouvertMax=" + decouvertMax
				+ ", debitMax=" + debitMax + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Personne getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Personne titulaire) {
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
