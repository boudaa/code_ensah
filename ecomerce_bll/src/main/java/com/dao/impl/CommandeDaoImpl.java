package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Commande;
import com.dao.CommandeDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

/**
 * Une implémentation du DAO pour l'entité produit. Cette implémentation se base
 * sur Spring (héritage de HibernateSpringGenericDaoImpl)
 * 
 * @author T.BOUDAA
 *
 */

@Repository
public class CommandeDaoImpl extends HibernateSpringGenericDaoImpl<Commande, Long> implements CommandeDao {

	public CommandeDaoImpl() {
		super(Commande.class);
	}

}
