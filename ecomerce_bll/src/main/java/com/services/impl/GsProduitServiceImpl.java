package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Produit;
import com.dao.ProduitDao;
import com.services.GsProduitService;

/**
 * Classe qui implemente les méthodes metiers de gestion de produits
 * 
 * @author T.Boudaa
 *
 */

@Service
@Transactional
public class GsProduitServiceImpl implements GsProduitService {

	@Autowired
	private ProduitDao produitDao;

	public void addProduit(Produit p) {

		// TODO: ajout des regles metier s'elles existent

		produitDao.create(p);

	}

	public List<Produit> getAllProducts() {
		return produitDao.getAll();
	}

}
