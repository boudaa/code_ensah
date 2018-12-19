package com.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DaoForTest {
	/** référence l'unique instance de Session Factory */
	private SessionFactory sf = SessionFactoryBuilder.getSessionFactory();

	/**
	 * Permet de persister l'objet passer en paramètre
	 * 
	 * @param etd objet à persister
	 */
	public void save(Object etd) {

		Session session = null;
		Transaction tx = null;

		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les opérations bases de données

			// enregistrer un étudiant dans la base de données
			session.save(etd);

			// On valide la transaction, ceci ferme également la session si on la récupérer
			// avec getCurrentSession
			// si elle est récupéré par openSession dans ce cas il faut appeler close de la
			// session explicitement
			tx.commit();

		} catch (HibernateException ex) {

			// Si il y a des problèmes et une transaction a été déjà crée on l'annule
			if (tx != null) {
				// Annulation d'une transaction
				tx.rollback();

			}

			// On n'oublie pas de remonter l'erreur originale
			throw ex;

		} finally {

			// Si la session n'est pas encore fermée par commit
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

	}
}
