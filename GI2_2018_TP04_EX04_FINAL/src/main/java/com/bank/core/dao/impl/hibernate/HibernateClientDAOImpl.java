package com.bank.core.dao.impl.hibernate;

import com.bank.core.bll.bo.Client;
import com.bank.core.dao.IClientDao;
import com.dao.base.hibernate.HibernateGenericDaoImpl;

/**
 * Classe d'implémentation d'un DAO pour Client
 * 
 * @author Tarik BOUDAA
 * 
 */
public class HibernateClientDAOImpl extends HibernateGenericDaoImpl<Client, Long> implements IClientDao {

	public HibernateClientDAOImpl() {
		super(Client.class);

	}

}
