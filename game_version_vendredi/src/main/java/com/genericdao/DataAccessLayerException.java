package com.genericdao;

public class DataAccessLayerException extends RuntimeException {

	public DataAccessLayerException() {
		super();

	}

	public DataAccessLayerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DataAccessLayerException(String message, Throwable cause) {
		super(message, cause);

	}

	public DataAccessLayerException(String message) {
		super(message);

	}

	public DataAccessLayerException(Throwable cause) {
		super(cause);

	}

}
