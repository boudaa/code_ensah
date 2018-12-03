package com.bank.core.bll;

/**
 * Classe d'exception qui présente une erreur lors d'une opération sur un compte
 * bancaire
 * 
 * @author boudaa
 *
 */
public class CompteOperationException extends Exception {

	public CompteOperationException() {
	}

	public CompteOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteOperationException(String message) {
		super(message);
	}

}
