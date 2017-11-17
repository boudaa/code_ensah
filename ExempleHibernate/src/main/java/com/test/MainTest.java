package com.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bo.Etudiant;

public class MainTest {
	SessionFactory sf = null;

	public MainTest() {
		sf = HibernateUtils.getSF();
	}

	public void update(Etudiant etd) {

		Session session = null;
		Transaction tx = null;
		try {

			session = sf.getCurrentSession();
			
			

			tx = session.beginTransaction();

			// Travail
			session.update(etd);

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();

			}

			throw ex;

		} finally {
			if (session != null) {
				//session.close();
			}

		}

	}

	public void delete(Long pId) {

		Session session = null;
		Transaction tx = null;

		Etudiant etd = null;
		try {

			session = sf.openSession();

			tx = session.beginTransaction();

			// Travail
			etd = (Etudiant) session.get(Etudiant.class, pId);

			session.delete(etd);

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();

			}

			throw ex;

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public List<Etudiant> finByName(String pString) {

		Session session = null;
		Transaction tx = null;

		List<Etudiant> list = null;
		try {

			session = sf.openSession();

			tx = session.beginTransaction();

			// Travail

			Query q = session.createQuery("from Etudiant where nom=? ");

			q.setParameter(0, pString);

			list = (List<Etudiant>) q.list();

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();

			}

			throw ex;

		} finally {
			if (session != null) {
				session.close();
			}

		}

		return list;

	}

	public Etudiant findById(Long pId) {

		Session session = null;
		Transaction tx = null;

		Etudiant etd = null;
		try {

			session = sf.openSession();

			tx = session.beginTransaction();

			// Travail
			etd = (Etudiant) session.get(Etudiant.class, pId);

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();

			}

			throw ex;

		} finally {
			if (session != null) {
				session.close();
			}

		}

		return etd;

	}

	public void save(Etudiant etd) {

		Session session = null;
		Transaction tx = null;
		try {

			session = sf.openSession();

			tx = session.beginTransaction();

			// Travail
			session.save(etd);

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();

			}

			throw ex;

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public static void main(String[] args) {

		MainTest test = new MainTest();
		//
		// Etudiant etd = new Etudiant();
		// etd.setNom("boudaa");
		// etd.setPrenom("Karim");
		//
		// new MainTest().save(etd);

		// Etudiant etd = test.findById(Long.valueOf(1));
		//
		// System.out.println(etd);
		//
		// List<Etudiant> list = test.finByName("boudaa");
		//
		// for (Etudiant it : list) {
		// System.out.println(it);
		// }
		//
		// test.delete(Long.valueOf(1));
		//
		// list = test.finByName("boudaa");
		//
		// System.out.println("Apres suppresion ");
		//
		// for (Etudiant it : list) {
		// System.out.println(it);
		// }

		Etudiant etd = test.findById(Long.valueOf(1));

		System.out.println(etd);

	}

}
