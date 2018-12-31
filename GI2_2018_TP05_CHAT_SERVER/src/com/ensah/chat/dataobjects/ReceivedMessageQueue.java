package com.ensah.chat.dataobjects;

import java.util.concurrent.LinkedBlockingQueue;

public class ReceivedMessageQueue {

	/** The unique instance of this class */
	private static final ReceivedMessageQueue instance = new ReceivedMessageQueue();

	/** The store messages */
	private LinkedBlockingQueue<ReceivedMessage> messageQueue = new LinkedBlockingQueue<ReceivedMessage>();

	private ReceivedMessageQueue() {
	}

	public static final ReceivedMessageQueue getIncomingMessageQueue() {
		return instance;
	}

	public synchronized int getQueueSize() {
		
	
		return messageQueue.size();
	}

	public synchronized ReceivedMessage takeMessage() {

		ReceivedMessage message = null;
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

	public void putMessageInTheQueue(ReceivedMessage pMessage) {

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
