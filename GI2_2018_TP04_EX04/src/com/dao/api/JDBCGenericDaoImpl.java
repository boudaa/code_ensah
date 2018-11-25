package com.dao.api;

import org.apache.log4j.Logger;

import com.exception.DataBaseException;

public abstract class JDBCGenericDaoImpl<T, PK> implements IGenericDao<T, PK> {

	public static final String SAVE_MSG_ERROR = "Error while trying to save the object";
	public static final String UPDATE_MSG_ERROR = "Error while trying to update the object";
	public static final String DELETE_MSG_ERROR = "Error while trying to delete the object with id = ";
	public static final String FIND_MSG_ERROR = "Error while trying  to find the object with id = ";
	public static final String FIND_ALL_MSG_ERROR = "Error while trying  to find all objects ";

	/** Logger pour la journalisation */
	protected final Logger LOG;

	private Class<?> daoClass;

	protected JDBCGenericDaoImpl(Class<?>  pClass) {
		daoClass = pClass;
		LOG = Logger.getLogger(daoClass);

	}

	public void handleDbOperationError(String message, Object pObj, Exception ex) throws DataBaseException {

		String obj;

		if (pObj == null) {
			obj = "";
		} else {
			obj = pObj.toString();
		}
		// on écrit dans le journal
		LOG.error(message + "  " + obj + "  due to exception : " + ex);
		// sans oublié de remonter la stack
		throw new DataBaseException(message + "  " + obj + " in " + daoClass.getSimpleName(), ex);
	}

	public void handleSaveError(Object pObj, Exception ex) throws DataBaseException {

		handleDbOperationError(SAVE_MSG_ERROR, pObj, ex);

	}

	public void handleUpdateError(Object pObj, Exception ex) throws DataBaseException {

		handleDbOperationError(UPDATE_MSG_ERROR, pObj, ex);

	}

	public void handleDeleteError(Object pObj, Exception ex) throws DataBaseException {

		handleDbOperationError(DELETE_MSG_ERROR, pObj, ex);

	}

	public void handleFindError(Object pObj, Exception ex) throws DataBaseException {

		handleDbOperationError(FIND_MSG_ERROR, pObj, ex);

	}

	public void handleFindAllError(Exception ex) throws DataBaseException {

		handleDbOperationError(FIND_ALL_MSG_ERROR, null, ex);

	}

}
