package com.ensah.chat.dataobjects;

import java.io.Serializable;

import com.ensah.chat.server.network.ConnectionToClient;

public class ReceivedMessage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectionToClient clientConnexion;
	private  Object message;
	public ReceivedMessage() {
	}
	public ReceivedMessage(Object obj) {
		message = obj;
	}

	@Override
	public String toString() {
		return message.toString();
	}
	public ConnectionToClient getClientConnection() {
		return clientConnexion;
	}
	public void setClientConnexion(ConnectionToClient playerConnection) {
		this.clientConnexion = playerConnection;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
	

}