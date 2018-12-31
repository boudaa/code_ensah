package com.ensah.chat.server.network;

public class ServerNetworkException extends RuntimeException {

	public ServerNetworkException() {
		super();
	}

	public ServerNetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerNetworkException(String message) {
		super(message);
	}

	public ServerNetworkException(Throwable cause) {
		super(cause);
	}

}
