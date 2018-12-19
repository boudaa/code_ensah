package com.dao.base.exceptions;

/**
 * Encapsule les erreurs base de données
 * @author Tarik BOUDAA
 *
 */
public class DataAccessLayerException extends RuntimeException {

    public DataAccessLayerException(String pMsg) {
	super(pMsg);

    }

    public DataAccessLayerException(String pMsg, Throwable pCause) {
	super(pMsg, pCause);
    }

    public DataAccessLayerException(Throwable pCause) {
	super(pCause);
    }

}
