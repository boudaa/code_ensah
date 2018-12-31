package com.ensah.chat.dataobjects;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageToSendQueue {

	/** The store messages */
	private LinkedBlockingQueue<CommunicationMessage> messageQueue = new LinkedBlockingQueue<CommunicationMessage>();

	public int getQueueSize() {
		return messageQueue.size();
	}

	public CommunicationMessage takeMessage() {

		CommunicationMessage message = null;
		try {
			message = messageQueue.take();
		} catch (InterruptedException e) {
			// TODO
			e.printStackTrace();

		} finally {
			// TODO
		}
		return message;

	}

	public void putMessageInTheQueue(CommunicationMessage pMessage) {
		try {
			messageQueue.put(pMessage);
		} catch (InterruptedException e) {
			// TODO
			e.printStackTrace();

		} finally {
			// TODO
		}

	}

}
