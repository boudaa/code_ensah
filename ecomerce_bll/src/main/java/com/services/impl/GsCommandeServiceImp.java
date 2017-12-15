package com.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Client;
import com.bo.Commande;
import com.bo.Panier;
import com.dao.CommandeDao;
import com.services.GsCommmandeService;

@Service
@Transactional
public class GsCommandeServiceImp implements GsCommmandeService {

	@Autowired
	private CommandeDao commandeDao;

	
	
	public void validerCommande(Panier panier, Client client) {

		

		Commande commande = new Commande();

		commande.setPanier(panier);
		commande.setClient(client);
		commande.setTotal(panier.getTotal());
		
		
		
		commandeDao.create(commande);

	}

}
