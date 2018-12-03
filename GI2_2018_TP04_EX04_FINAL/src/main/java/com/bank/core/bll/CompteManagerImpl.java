package com.bank.core.bll;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bank.core.bll.bo.Client;
import com.bank.core.bll.bo.Compte;
import com.bank.core.dao.AbstractDaoFactory;
import com.bank.core.dao.DataAccessLayerException;
import com.bank.core.dao.ICompteDao;
import com.bank.core.dao.ObjectNotFoundException;
import com.dao.base.TransactionManager;
import com.dao.base.TransactionManagerFactory;

/**
 * Cette classe offre les services de gestion des comptes
 * 
 * 
 * 
 * @author boudaa
 *
 */
public class CompteManagerImpl implements CompteManger {

	/** pour la journalisation */
	private Logger LOGGER;

	/**
	 * instance du dao pour gérer les opérations d'accès aux données suer les Compte
	 */
	private ICompteDao compteDao = (ICompteDao) AbstractDaoFactory.getFactory().getCompteDao();

	public CompteManagerImpl() {
		// on initialise le logger
		Logger.getLogger(getClass());
	}

	public void crediter(Compte c, double m) throws DataAccessLayerException {

		LOGGER.info("Le compte " + c.getId() + " a réalisé une opération créditer");

		c.setSolde(c.getSolde() + m);

		// On persiste cette mise à jour en base de données
		compteDao.update(c);

	}

	public Compte getCompteByNum(Long num) throws DataAccessLayerException, ObjectNotFoundException {

		// On fait appel ou dao pour obtenir le compte
		return compteDao.find(num);

	}

	public void debiter(Compte c, double m) throws CompteOperationException, DataAccessLayerException {

		// Si les regles metiers sont respectées
		if (m > c.getMaxDebit()) {

			LOGGER.error("Le compte " + c.getId()
					+ " essaie de faire une Opération impossible car le débit maximal est atteint");

			// on remonte cette erreur fonctionnelle à la couche gui
			throw new CompteOperationException("Opération impossible car votre débit maximal est " + c.getMaxDebit());

		}

		// toujours dans le cadre de vérification des regles metiers
		if (c.getSolde() - m < -c.getMaxDecouvert()) {

			// on remontre cette erreur fonctionnelle sous forme d'exception à la couche
			// supérieur
			throw new CompteOperationException("Le débit est impossible car solde- m < dm");
		}

		// si els regles de cette opérations sont toutes respectées alors on fait la
		// modifcation
		c.setSolde(c.getSolde() - m);

		// on persiste cette modification en base de données par l'appel du dao
		compteDao.update(c);

	}

	public Compte createCompte(Client p) throws DataAccessLayerException {

		// création d'un objet transitoire qui présente un comte
		Compte c = new Compte();

		// on lui affecte le titulaire
		c.setTitulaire(p);

		// l'id est généré automatiquement par auto-incrément
		// c.setId(Long.valueOf(generateIdCompte()));

		// appel du dao pour persister l'objet
		compteDao.add(c);

		// on retourne le compte crée
		return c;

	}

	public void virement(Compte ce, Compte cr, double m) throws CompteOperationException {

		// on obtient un transaction manager
		TransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();
		try {
			try {

				// On commence une transaction
				transactionManager.beginTransaction();

				// effectuer les deux opérations dans une transaction
				debiter(ce, m);
				crediter(cr, m);

				// on valide la transaction
				transactionManager.commit();

			} finally {

				transactionManager.setAutoCommitTrue();
			}

		} catch (DataAccessLayerException e) {

			try {
				// On annule la transaction
				transactionManager.rollback();
			} catch (DataAccessLayerException ex) {
				// TODO : à faire
			}

		}
		LOGGER.info("Le compte " + ce.getId() + " a réalisé  un virement vers " + cr.getId());

	}

	public List<Compte> showBanque() throws DataAccessLayerException {

		// on fait appel au dao pour retrouver la liste des comptes
		List<Compte> listeComptes = compteDao.getAll();

		return listeComptes;

	}

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
