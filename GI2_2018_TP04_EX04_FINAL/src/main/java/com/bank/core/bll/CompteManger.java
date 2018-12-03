package com.bank.core.bll;

import java.util.List;

import com.bank.core.bll.bo.Client;
import com.bank.core.bll.bo.Compte;
import com.bank.core.dao.DataAccessLayerException;
import com.bank.core.dao.ObjectNotFoundException;

/**
 * Interface d'un service metier
 * 
 * @author boudaa
 *
 */
public interface CompteManger extends IManager {
	/**
	 * créditer un compte
	 * 
	 * @param c un compte
	 * @param m le montant
	 * @throws DataAccessLayerException
	 */
	void crediter(Compte c, double m) throws DataAccessLayerException;

	/**
	 * Récupére un compte par son numéro
	 * 
	 * @param num
	 * @return
	 * @throws CompteNotFoundException
	 * @throws ObjectNotFoundException
	 * @throws DataAccessLayerException
	 */
	Compte getCompteByNum(Long num) throws DataAccessLayerException, ObjectNotFoundException;

	/**
	 * Débiter un compte
	 * 
	 * @param c le compte é débiter
	 * @param m le montant
	 * @throws CompteOperationException
	 * @throws DataAccessLayerException
	 */
	void debiter(Compte c, double m) throws CompteOperationException, DataAccessLayerException;

	/**
	 * Permet de créer un compte
	 * 
	 * @param p une personne
	 * @throws DataAccessLayerException
	 */
	Compte createCompte(Client p) throws DataAccessLayerException;

	/**
	 * Permet de faire un virement
	 * 
	 * @param ce
	 * @param cr
	 * @param m
	 * @throws CompteOperationException
	 * @throws DataAccessLayerException
	 */
	void virement(Compte ce, Compte cr, double m) throws CompteOperationException;

	/**
	 * retourne la liste des comptes
	 * 
	 * @throws DataAccessLayerException
	 */
	List<Compte> showBanque() throws DataAccessLayerException;

	/**
	 * méthode interne pour générer les numéro aléatoirement
	 * 
	 * @return
	 */
	int generateIdCompte();
}
