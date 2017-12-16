package com.exceptions;

public class AuthentificationFailureException extends Exception {

	public AuthentificationFailureException() {
		super();
	}

	public AuthentificationFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public AuthentificationFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthentificationFailureException(String message) {
		super(message);
	}

	public AuthentificationFailureException(Throwable cause) {
		super(cause);
	}

}
