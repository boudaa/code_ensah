package com.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.ensah.chat.client.gui.Observer;

/**
 * Classe qui permet de gérer la communication avec le serveur
 * 
 * @author boudaa
 *
 */
public class ConnectionToServer implements Observable {

	private List<Observer> observers = new ArrayList<Observer>();

	/** identifiant assigné par le serveur à ce client */
	private final int clientID;

	/** socket de connexion au serveur */
	private final Socket socket;

	/**
	 * le flux d'entrée liée à la socket, il est utilisé pour l'envoi des messages
	 */
	private final ObjectInputStream in;

	/**
	 * le flux de sortie liée à la socket, il est utilisé pour la reception des
	 * messages
	 */
	private final ObjectOutputStream out;

	/** thread utilisé pour envoyer les messages au serveur */
	private final SendThread sendThread;

	/** thread utilisé pour recevoir les messages au serveur */
	private final ReceiveThread receiveThread;

	/** file d'attente des messages à envoyer */
	private final LinkedBlockingQueue<Object> outgoingMessages;

//	/** file d'attente des messages reus */
//	private final LinkedBlockingQueue<Object> incomingMessages;

	public ConnectionToServer(String host, int port) throws UnknownHostException, IOException, ClassNotFoundException {

		// initialisation de la liste des messages à envoyer

		outgoingMessages = new LinkedBlockingQueue<Object>();
//		incomingMessages = new LinkedBlockingQueue<Object>();

		// créer la socket de connexion au serveur
		socket = new Socket(host, port);

		// on obtient le flux de sortie prmettant d'envoyer les messages via cette
		// socket
		out = new ObjectOutputStream(socket.getOutputStream());

		// on obtient le flux d'entrée prmettant de recevoir les messages via cette
		// socket
		in = new ObjectInputStream(socket.getInputStream());

		// On obtient l'identifiant génré par le serveur
		Object response = in.readObject();
		clientID = ((Integer) response).intValue();

		// On envoit un message indiquant qu'un nouveau client vient de se connecter
		out.writeObject("###### L'utilisateur d'id=" + clientID + " vient de rejoindre le salon de discussion ###### ");
		out.flush();

		// création et démarrage des threads de reception et d'envoi
		sendThread = new SendThread();
		receiveThread = new ReceiveThread();
		sendThread.start();
		receiveThread.start();
	}

	/**
	 * Cette méthode permet d'envoyer les messages au serveur
	 * 
	 * @param message
	 */
	public void send(Object message) {

		// inserer le message dans la file d'envoi
		outgoingMessages.add(message);
	}

	/**
	 * Cette classe définit le thread d'envoi des messages
	 */
	private class SendThread extends Thread {
		public void run() {
			try {
				while (true) {

					// prendre un message de la file des messages en attente
					Object message = outgoingMessages.take();
					// Puis écrire dans le flux de sortie
					out.writeObject(message);
					out.flush();

				}
			} catch (Exception e) {

				throw new ClientNetworkException(e);
			}

		}
	}

	/**
	 * Cette classe définit le thread de reception des messages
	 */
	private class ReceiveThread extends Thread {
		public void run() {
			try {
				while (true) {
					// lire un message à partir du flux d'entrée
					Object obj = in.readObject();

					// notifier les observateurs (ici le gui) pour qu'ils soient mis à jour
					notifyAllObservers(obj);

					// Attente
					sleep(1000);
				}
			} catch (Exception e) {

				throw new ClientNetworkException(e);
			}
		}
	}

	/**
	 * Permet de notifier tous les observateurs
	 */
	public synchronized void notifyAllObservers(Object state) {

		for (Observer it : observers) {
			it.update(state);
		}
	}

	/**
	 * enregistrer un observateur sur cet observale
	 */
	public void addObserver(Observer pObserver) {
		observers.add(pObserver);

	}

}