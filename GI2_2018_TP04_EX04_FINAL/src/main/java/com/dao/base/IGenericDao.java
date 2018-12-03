package com.dao.base;

import java.util.List;

import com.bank.core.dao.DataAccessLayerException;
import com.bank.core.dao.ObjectNotFoundException;

/**
 * Interface des DAO génériques
 * 
 * @author Tarik BOUDAA
 *
 * @param <T,
 *            PK>
 */
public interface IGenericDao<T, PK> extends IDao {

	/**
	 * Méthode permettant la sauvegarde d'un objet de type T pasé en pramétre à la
	 * méthode
	 * 
	 * @param obj
	 *            l'objet à pérsister
	 * @return
	 */
	public abstract PK add(T obj) throws DataAccessLayerException;

	/**
	 * Méthode pour supprimer un objet dont l'id est passé en paramétre
	 * 
	 * @param id
	 *            l'identifiant de l'objet é supprimer
	 * @return
	 */
	public abstract void delete(PK id) throws DataAccessLayerException;

	/**
	 * Méthode de mise à jour d'un objet passé en paramétre
	 * 
	 * @param obj
	 *            contient la nouvelle version de l'objet avec laquelle la mise é
	 *            jour sera effectuée
	 * @return
	 */
	public abstract void update(T obj) throws DataAccessLayerException;

	/**
	 * Méthode pour rechercher une entité par son identifiant
	 * 
	 * @param id
	 *            identifiant de l'entité recherchée
	 * @return
	 */
	public abstract T find(PK id) throws DataAccessLayerException, ObjectNotFoundException;

	/**
	 * Méthode pour récupérer toutes les entités de type T
	 * 
	 * @return
	 */
	public abstract List<T> getAll() throws DataAccessLayerException;

}
