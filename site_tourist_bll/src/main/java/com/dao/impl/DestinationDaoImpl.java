package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Destination;
import com.dao.DestinationDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

@Repository
public class DestinationDaoImpl extends HibernateSpringGenericDaoImpl<Destination, Long> implements DestinationDao {

	public DestinationDaoImpl() {
		super(Destination.class);
	}

}
