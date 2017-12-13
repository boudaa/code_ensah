package com.decorators;

import org.displaytag.decorator.TableDecorator;

import com.bo.Produit;

public class PanierActionsDecorator extends TableDecorator {

	private String addToPanierLink;

	public String getAddToPanierLink() {

		Produit produit = (Produit) getCurrentRowObject();
		Long idprod = produit.getId();

		this.addToPanierLink = "<a href=\"addProduitToPanier?idProduit=" + idprod + "\">ajouter au panier</a>";

		return this.addToPanierLink;
	}

}