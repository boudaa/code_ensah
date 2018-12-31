package com.ensah.chat.dataobjects;

import java.io.Serializable;

public class CommunicationMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	public final Object message;
	public final int senderID;

	public CommunicationMessage(int senderID, Object message) {
		this.senderID = senderID;
		this.message = message;
	}

}
