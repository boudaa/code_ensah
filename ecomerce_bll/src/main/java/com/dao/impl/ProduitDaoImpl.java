package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Produit;
import com.dao.ProduitDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

/**
 * Une implémentation du DAO pour l'entité produit. Cette implémentation se base
 * sur Spring (héritage de HibernateSpringGenericDaoImpl)
 * 
 * @author T.BOUDAA
 *
 */

@Repository
public class ProduitDaoImpl extends HibernateSpringGenericDaoImpl<Produit, Long> implements ProduitDao {

	public ProduitDaoImpl() {
		super(Produit.class);
	}

}
