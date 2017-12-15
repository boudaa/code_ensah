package com.bo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LignePanier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "produit_fk")
	private Produit produit;

	private int quantite;

	private double montant;

	public LignePanier() {
	}

	public LignePanier(Produit p, int pQuantite) {

		quantite = pQuantite;
		produit = p;
		montant = p.getPrix() * quantite;
	}

	@Override
	public String toString() {
		return "LignePanier [id=" + id + ", produit=" + produit + ", quantite=" + quantite + ", montant=" + montant
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

}
