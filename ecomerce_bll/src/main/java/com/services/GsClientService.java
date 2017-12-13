package com.services;

import java.util.List;

import com.bo.Client;

/**
 * interface du service metier permettant d'implémenter la logique métier et
 * applicative liées à la gestion des produits
 * 
 * @author T.BOUDAA
 *
 */
public interface GsClientService {

	/** implemente le service métier permettant de sauvgarder un produit */
	void addClient(Client p);

	/** implémente le service métier permettant de récupérer tous les produits */
	List<Client> getAllClients();

}