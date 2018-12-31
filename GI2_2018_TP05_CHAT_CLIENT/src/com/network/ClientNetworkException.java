package com.network;

public class ClientNetworkException extends RuntimeException {

	public ClientNetworkException() {
		super();
	}

	public ClientNetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientNetworkException(String message) {
		super(message);
	}

	public ClientNetworkException(Throwable cause) {
		super(cause);
	}

}
