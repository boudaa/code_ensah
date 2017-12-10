package com.genericdao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.genericdao.api.GenericDao;
import com.genericdao.common.DataAccessLayerException;
import com.genericdao.common.EntityNotFoundException;
import com.genericdao.utils.SessionFactoryBuilderHibernate3;
import com.genericdao.utils.SessionFactoryBuilderHibernate4;

/**
 * Classe de base pour tous les DAOs, elle implémente les méthodes CRUD
 * génériques définit par le contrat GenericDAO<T>. Cette implémentation est
 * basée sur Hibernate nativement
 * 
 * @author <a href="mailto:tarik.boudaa@gmail.com">T.BOUDAA Ecole Nationale des
 *         Sciences Appliquées </a>
 * 
 * @param <T>
 *            le type d'objet métier manipulé
 * @param <PK>
 *            le type utilisé pour l'indentifiant d'un objet métier
 */
public abstract class NativeHibernateGenericDAOImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	/** La classe BO manipulé par le DAO */
	protected Class<T> boClass;

	/** Utilisé par tous les DAOs */
	protected final Logger LOGGER;

	/** la fabrique des session */
	protected SessionFactory sf = SessionFactoryBuilderHibernate4.getSessionFactory();

	public NativeHibernateGenericDAOImpl(Class<T> pClass) {

		boClass = pClass;

		LOGGER = Logger.getLogger(boClass);

		LOGGER.debug("le dao de " + boClass + " a été initialisé");
	}

	/**
	 * Annule la transaction
	 * 
	 * @param tx
	 * @param ex
	 * @throws Exception
	 */
	protected void handleDaoOpError(Transaction tx, RuntimeException ex) {

		if (tx != null) {
			tx.rollback();
		}

		// On trace l'erreur
		LOGGER.error("erreur é cause de l'exception " + ex);

		// On remonte l'exception
		throw new DataAccessLayerException(ex);

	}

	protected void closeSession(Session s) {

		if (s != null && s.isOpen()) {
			s.close();
		}
	}

	protected Session getSession() {
		return sf.getCurrentSession();
	}

	protected boolean anActiveTransactionExists(Session s) {
		if (s != null && s.getTransaction() != null)

			return s.getTransaction().isActive();

		return false;
	}

	public PK create(T o) {

		LOGGER.debug("appel de la méthode create");

		// On obtient la session en cours
		Session s = getSession();

		PK id = null;

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {
			LOGGER.debug("le DAO utilise la transaction  BLL");

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction
			id = (PK) s.save(o);

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				id = (PK) s.save(o);

				// On valide la transaction
				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		LOGGER.debug("fin d'appel de la méthode save avec succés ");

		return id;
	}

	public void update(T o) {

		LOGGER.debug("appel de la méthode save");

		// On obtient la session en cours
		Session s = getSession();

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction
			s.update(o);

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				s.update(o);

				// On valide la transaction
				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		LOGGER.debug("fin d'appel de la méthode save avec succés ");

	}

	public List<T> getAll() throws EntityNotFoundException {

		LOGGER.debug("appel de la méthode save");

		// On obtient la session en cours
		Session s = getSession();

		List<T> list = null;

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction

			list = getSession().createCriteria(boClass).list();

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				list = getSession().createCriteria(boClass).list();

				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		if (list == null || list.size() == 0)
			throw new EntityNotFoundException();

		return list;
	}

	public List<T> getEntityByColValue(String pColName, String pColVal, String pClassName)
			throws EntityNotFoundException {

		LOGGER.debug("appel de la méthode getByColValue");

		// On obtient la session en cours
		Session s = getSession();

		List<T> list = new ArrayList<T>();

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction
			Query q = s.createQuery("from " + pClassName + " where " + pColName + "=?");
			q.setParameter(0, pColVal);
			list = q.list();

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				Query q = s.createQuery("from " + pClassName + " where " + pColName + "=?");
				q.setParameter(0, pColVal);
				list = q.list();

				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		return list;
	}

	public void delete(PK pId) throws EntityNotFoundException {

		LOGGER.debug("appel de la méthode delete");

		T obj = (T) findById(pId);

		// On obtient la session en cours
		Session s = getSession();

		PK id = null;

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction
			s.delete(obj);

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				s.delete(obj);

				// On valide la transaction
				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		LOGGER.debug("fin d'appel de la méthode delete avec succés ");

	}

	public List<T> getAllDistinct() throws EntityNotFoundException {

		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);

	}

	public T findById(PK pId) throws EntityNotFoundException {
		LOGGER.debug("appel de la méthode findById");

		// On obtient la session en cours
		Session s = getSession();

		T obj = null;

		// Si la couche BLL a démarré une transaction
		if (anActiveTransactionExists(s)) {

			// Dans ce cas c'est la couche BLL qui gere la session et la
			// transaction
			obj = (T) s.get(boClass, pId);

		} else {
			LOGGER.debug("le DAO initialise sa propre transaction");

			Transaction tx = null;

			try {

				// On démarre une transaction localement
				tx = s.beginTransaction();

				obj = (T) s.get(boClass, pId);

				tx.commit();
			} catch (RuntimeException ex) {
				// En cas de problèmes en annule la transaction
				handleDaoOpError(tx, ex);
			} finally {
				// Fermer la session s'elle est encore ouverte
				closeSession(s);
			}
		}

		if (obj == null)
			throw new EntityNotFoundException();

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
