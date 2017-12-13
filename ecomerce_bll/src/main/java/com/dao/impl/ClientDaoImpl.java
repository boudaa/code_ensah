package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Client;
import com.dao.ClientDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

/**
 * Une implémentation du DAO pour l'entité produit. Cette implémentation se base
 * sur Spring (héritage de HibernateSpringGenericDaoImpl)
 * 
 * @author T.BOUDAA
 *
 */

@Repository
public class ClientDaoImpl extends HibernateSpringGenericDaoImpl<Client, Long> implements ClientDao {

	public ClientDaoImpl() {
		super(Client.class);
	}

}
