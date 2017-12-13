package com.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panier_id")
	private List<LignePanier> lignePanierList = new ArrayList<LignePanier>();

	private double total;

	public void addToPanier(LignePanier lp) {

		lignePanierList.add(lp);
		total += lp.getMontant();
	}

	public int getNbrArticle() {
		return lignePanierList.size();
	}

	@Override
	public String toString() {
		return "Panier [id=" + id + ", lignePanierList=" + lignePanierList + ", total=" + total + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<LignePanier> getLignePanierList() {
		return lignePanierList;
	}

	public void setLignePanierList(List<LignePanier> lignePanierList) {
		this.lignePanierList = lignePanierList;
	}

	public double getTotal() {
		return total;
	}

}
