package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Categorie;
import com.bo.Client;
import com.bo.Produit;
import com.dao.CategorieDao;
import com.dao.ClientDao;
import com.dao.ProduitDao;
import com.services.GsCategorieService;
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
public class GsCategorieServiceImpl implements GsCategorieService {

	@Autowired
	private CategorieDao categorieDao;

	public void addCategorie(Categorie p) {

		// TODO: ajout des regles metier s'elles existent

		categorieDao.create(p);

	}

	public List<Categorie> getAllCategories() {
		return categorieDao.getAll();
	}

}
