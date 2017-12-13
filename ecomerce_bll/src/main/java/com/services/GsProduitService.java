package com.services;

import java.util.List;

import com.bo.Produit;

/**
 * interface du service metier permettant d'implémenter la logique métier et
 * applicative liées à la gestion des produits
 * 
 * @author T.BOUDAA
 *
 */
public interface GsProduitService {

	/** implemente le service métier permettant de sauvgarder un produit */
	void addProduit(Produit p);

	/**
	 * implémente le service métier permettant de récupérer tous les produits
	 */
	List<Produit> getAllProducts();

	Produit getProduitById(Long pId);

}