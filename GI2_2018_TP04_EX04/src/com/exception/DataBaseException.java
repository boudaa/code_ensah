package com.exception;

/**
 * Encapsule les erreurs base de données
 * @author Tarik BOUDAA
 *
 */
public class DataBaseException extends Exception {

    public DataBaseException(String pMsg) {
	super(pMsg);

    }

    public DataBaseException(String pMsg, Throwable pCause) {
	super(pMsg, pCause);
    }

    public DataBaseException(Throwable pCause) {
	super(pCause);
    }

}
