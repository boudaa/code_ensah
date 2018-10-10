package com.bll;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bo.Compte;
import com.dao.DaoFactory;
import com.dao.TransactionManager;
import com.dao.TransactionManagerFactory;
import com.dao.interfaces.CompteDao;
import com.bo.Client;
import com.exception.CompteOperationException;
import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;

/**
 * Cette classe offre les services de gestion des comptes
 * 
 * @author boudaa
 *
 */
public class CompteManagerImpl {

	private Logger LOGGER = Logger.getLogger(CompteManagerImpl.class);

	// On obtient une instance des DAOs
	private CompteDao compteDao = (CompteDao) DaoFactory.getDao("com.dao.impl.jdbc.CompteDAOImpl");

	/**
	 * créditer un compte
	 * 
	 * @param c
	 *            un compte
	 * @param m
	 *            le montant
	 * @throws DataBaseException
	 */
	public void crediter(Compte c, double m) throws DataBaseException {

		LOGGER.info("Le compte " + c.getNum() + " a réalisé une opération créditer");

		c.setSolde(c.getSolde() + m);

		compteDao.update(c);

	}

	/**
	 * Récupère un compte par son numéro
	 * 
	 * @param num
	 * @return
	 * @throws CompteNotFoundException
	 * @throws ObjectNotFoundException
	 * @throws DataBaseException
	 */
	public Compte getCompteByNum(Long num) throws DataBaseException, ObjectNotFoundException {

		return compteDao.find(num);

	}

	/**
	 * Débiter un compte
	 * 
	 * @param c
	 *            le compte à débiter
	 * @param m
	 *            le montant
	 * @throws CompteOperationException
	 * @throws DataBaseException
	 */
	public void debiter(Compte c, double m) throws CompteOperationException, DataBaseException {

		if (m > c.getDebitMax()) {

			LOGGER.error("Le compte " + c.getNum()
					+ " essaie de faire une Opération impossible car le débit maximal est atteint");

			throw new CompteOperationException("Opération impossible car votre débit maximal est " + c.getDebitMax());
		}

		if (c.getSolde() - m < -c.getDecouvertMax()) {

			throw new CompteOperationException("Le débit est impossible car solde- m < dm");
		}

		c.setSolde(c.getSolde() - m);

		compteDao.update(c);

	}

	/**
	 * Permet de créer un compte
	 * 
	 * @param p
	 *            une personne
	 * @throws DataBaseException
	 */
	public Compte createCompte(Client p) throws DataBaseException {

		Compte c = new Compte();

		c.setTitulaire(p);

		c.setNum(Long.valueOf(generateIdCompte()));

		compteDao.add(c);

		return c;

	}

	/**
	 * Permet de faire un virement
	 * 
	 * @param ce
	 * @param cr
	 * @param m
	 * @throws CompteOperationException
	 * @throws DataBaseException
	 */
	public void virement(Compte ce, Compte cr, double m) throws CompteOperationException {

		TransactionManager transactionManager = TransactionManagerFactory.getTransactionManager("jdbc");
		try {
			try {

				// On commence une transaction
				transactionManager.beginTransaction();

				debiter(ce, m);
				crediter(cr, m);

				transactionManager.commit();

			} finally {
				transactionManager.setAutoCommitTrue();
			}

		} catch (DataBaseException e) {

			try {
				transactionManager.rollback();
			} catch (DataBaseException ex) {
				// TODO : on essaie de faire qlq chose
			}

		}
		LOGGER.info("Le compte " + ce.getNum() + " a réalisé un virement vers " + cr.getNum());

	}

	/**
	 * retourne la liste des comptes
	 * 
	 * @throws DataBaseException
	 */
	public List<Compte> showBanque() throws DataBaseException {

		List<Compte> listeComptes = compteDao.getAll();

		return listeComptes;

	}

	/**
	 * méthode interne pour générer les numéro aléatoirement
	 * 
	 * @return
	 */
	public int generateIdCompte() {

		Random r = new Random();
		int i = 0;
		int id = 0;
		while (i < 8) {
			id += r.nextInt(10) * (int) Math.pow(10, i);
			i++;

		}

		return id;

	}

}
