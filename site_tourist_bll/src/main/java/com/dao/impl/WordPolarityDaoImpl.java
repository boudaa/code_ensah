package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.WordPolarity;
import com.dao.WordPolarityDao;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

@Repository
public class WordPolarityDaoImpl extends HibernateSpringGenericDaoImpl<WordPolarity, Long> implements WordPolarityDao {

	public WordPolarityDaoImpl() {
		super(WordPolarity.class);
	}

}
