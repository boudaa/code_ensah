package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Categorie;

import com.dao.CategorieDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

/**
 * Une implémentation du DAO pour l'entité produit. Cette implémentation se base
 * sur Spring (héritage de HibernateSpringGenericDaoImpl)
 * 
 * @author T.BOUDAA
 *
 */

@Repository
public class CategorieDaoImpl extends HibernateSpringGenericDaoImpl<Categorie, Long> implements CategorieDao {

	public CategorieDaoImpl() {
		super(Categorie.class);
	}

}
