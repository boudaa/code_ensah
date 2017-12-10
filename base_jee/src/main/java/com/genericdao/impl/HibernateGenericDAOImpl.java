package com.genericdao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genericdao.api.GenericDao;
import com.genericdao.common.EntityNotFoundException;

/**
 * Classe de base pour tous les DAOs, elle implémente les méthodes CRUD
 * génériques définit par le contrat GenericDAO<T>. Cette implémentation est
 * basée sur Hibernate. Cette implémentation necessite l'activation d'un
 * intercepteur au niveau de la couche service pour gérer les transactions,
 * ainsi elle ne peut pas etre utilisée pour gérer la transaction localement au
 * niveau DAO
 * 
 * @author <a href="mailto:tarik.boudaa@gmail.com">T.BOUDAA Ecole Nationale des
 *         Sciences Appliquées </a>
 * 
 * @param <T>
 *            le type d'objet métier manipulé
 * @param <PK>
 *            le type utilisé pour l'indentifiant d'un objet métier
 */

@Repository
public abstract class HibernateGenericDAOImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	/** La classe des objets BO manipulé par le DAO */
	protected Class<T> boClass;

	/** Utilisé par tous les DAOs */
	// protected final Logger LOGGER;

	/** la fabrique des session injectée via spring */
	@Autowired
	private SessionFactory sessionFactory;

	public HibernateGenericDAOImpl() {
		// LOGGER = Logger.getLogger(boClass);

		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		boClass = (Class) pt.getActualTypeArguments()[0];

		// LOGGER.debug("le dao de " + boClass + " a été initialisé");

	}

	public HibernateGenericDAOImpl(Class<T> pClass) {
		// LOGGER = Logger.getLogger(boClass);

		boClass = pClass;

		// LOGGER.debug("le dao de " + boClass + " a été initialisé");
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public PK create(T o) {

		PK id = (PK) getSession().save(o);

		return id;
	}

	public void update(T o) {

		getSession().update(o);

	}

	public List<T> getAll() {

		return getSession().createCriteria(boClass).list();

	}

	public List<T> getEntityByColValue(String pColName, String pColVal, String pClassName) {

		Query q = getSession().createQuery("from " + pClassName + " where " + pColName + "=?");
		q.setParameter(0, pColVal);

		return q.list();
	}

	public void delete(PK pId) {

		T obj = (T) findById(pId);

		getSession().delete(obj);

	}

	public List<T> getAllDistinct() {

		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);

	}

	public T findById(PK pId) {

		T obj = (T) getSession().get(boClass, pId);

		if (obj == null) {
			throw new EntityNotFoundException();

		}

		return obj;
	}

	public boolean exists(PK pId) {

		try {
			findById(pId);

		} catch (EntityNotFoundException ex) {
			return false;
		}

		return true;

	}

}
