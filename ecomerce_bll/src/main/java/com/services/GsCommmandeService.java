package com.services;

import com.bo.Client;
import com.bo.Commande;
import com.bo.Panier;

/**
 * interface du service metier permettant d'implémenter la logique métier et
 * applicative liées à la gestion des commandes
 * 
 * @author T.BOUDAA
 *
 */
public interface GsCommmandeService {

	/** Permet de valider une commande */
	void validerCommande(Panier p, Client c);

}