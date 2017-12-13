package com.services;

import java.util.List;

import com.bo.Categorie;

/**
 * interface du service metier permettant d'implémenter la logique métier et
 * applicative liées à la gestion des produits
 * 
 * @author T.BOUDAA
 *
 */
public interface GsCategorieService {

	/** implemente le service métier permettant de sauvgarder un produit */
	void addCategorie(Categorie p);

	/** implémente le service métier permettant de récupérer tous les produits */
	List<Categorie> getAllCategories();

}