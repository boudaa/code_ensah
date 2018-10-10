package com.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;

/**
 * Interface des DAO génériques
 * 
 * @author Tarik BOUDAA
 *
 * @param <T,
 *            PK>
 */
public interface GenericDao<T, PK> extends Dao {

	/**
	 * Méthode permettant la sauvegarde d'un objet de type T passé en pramètre à la
	 * méthode
	 * 
	 * @param obj
	 *            l'objet à pérsister
	 * @return
	 */
	public abstract Long add(T obj) throws DataBaseException;

	/**
	 * Méthode pour supprimer un objet dont l'id est passé en paramètre
	 * 
	 * @param id
	 *            l'identifiant de l'objet à supprimer
	 * @return
	 */
	public abstract void delete(PK id) throws DataBaseException;

	/**
	 * Méthode de mise à jour d'un objet passé en paramètre
	 * 
	 * @param obj
	 *            contient la nouvelle version de l'objet avec laquelle la mise à
	 *            jour sera effectuée
	 * @return
	 */
	public abstract void update(T obj) throws DataBaseException;

	/**
	 * Méthode pour rechercher une entité par son identifiant
	 * 
	 * @param id
	 *            identifiant de l'entité recherchée
	 * @return
	 */
	public abstract T find(PK id) throws DataBaseException, ObjectNotFoundException;


	/**
	 * Méthode pour récupérer toutes les entités de type T
	 * 
	 * @return
	 */
	public abstract List<T> getAll() throws DataBaseException;

}
