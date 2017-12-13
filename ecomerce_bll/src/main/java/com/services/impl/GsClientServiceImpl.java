package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Client;
import com.bo.Produit;
import com.dao.ClientDao;
import com.dao.ProduitDao;
import com.services.GsClientService;
import com.services.GsProduitService;

/**
 * Classe qui implemente les méthodes metiers de gestion de produits
 * 
 * @author T.Boudaa
 *
 */

@Service
@Transactional
public class GsClientServiceImpl implements GsClientService {

	@Autowired
	private ClientDao clientDao;

	public void addClient(Client p) {

		// TODO: ajout des regles metier s'elles existent

		clientDao.create(p);

	}

	public List<Client> getAllClients() {
		return clientDao.getAll();
	}

}
