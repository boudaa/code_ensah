package com.dao.base.jdbc;

import org.apache.log4j.Logger;

import com.dao.base.IGenericDao;
import com.dao.base.exceptions.DataAccessLayerException;
import com.dao.base.hibernate.HibernateGenericDaoImpl;

/**
 * Classe générique qui devrait implémenter les méthodes d'un dao d'une facon
 * générique avec JDBC (ce qui'est pas fait, mais elle est fait dans une autre
 * classe @see {@link HibernateGenericDaoImpl})
 * 
 * Cette classe offre un certain nombre de méthode communes aux Dao JDBC
 * 
 * @author boudaa
 *
 * @param <T>
 * @param <PK>
 */
public abstract class JDBCGenericDaoImpl<T, PK> implements IGenericDao<T, PK> {

	public static final String SAVE_MSG_ERROR = "Error while trying to save the object";
	public static final String UPDATE_MSG_ERROR = "Error while trying to update the object";
	public static final String DELETE_MSG_ERROR = "Error while trying to delete the object with id = ";
	public static final String FIND_MSG_ERROR = "Error while trying  to find the object with id = ";
	public static final String FIND_ALL_MSG_ERROR = "Error while trying  to find all objects ";

	/** Logger pour la journalisation */
	protected final Logger LOG;

	private Class<?> boClass;

	protected JDBCGenericDaoImpl(Class<?> pClass) {
		boClass = pClass;
		LOG = Logger.getLogger(boClass);

	}

	public void handleDbOperationError(String message, Object pObj, Exception ex) throws DataAccessLayerException {

		String obj;

		if (pObj == null) {
			obj = "";
		} else {
			obj = pObj.toString();
		}
		// on écrit dans le journal
		LOG.error(message + "  " + obj + "  due to exception : " + ex);
		// sans oublier de remonter la stack
		throw new DataAccessLayerException(message + "  " + obj + " in " + boClass.getSimpleName(), ex);
	}

	public void handleSaveError(Object pObj, Exception ex) throws DataAccessLayerException {

		handleDbOperationError(SAVE_MSG_ERROR, pObj, ex);

	}

	public void handleUpdateError(Object pObj, Exception ex) throws DataAccessLayerException {

		handleDbOperationError(UPDATE_MSG_ERROR, pObj, ex);

	}

	public void handleDeleteError(Object pObj, Exception ex) throws DataAccessLayerException {

		handleDbOperationError(DELETE_MSG_ERROR, pObj, ex);

	}

	public void handleFindError(Object pObj, Exception ex) throws DataAccessLayerException {

		handleDbOperationError(FIND_MSG_ERROR, pObj, ex);

	}

	public void handleFindAllError(Exception ex) throws DataAccessLayerException {

		handleDbOperationError(FIND_ALL_MSG_ERROR, null, ex);

	}

}
