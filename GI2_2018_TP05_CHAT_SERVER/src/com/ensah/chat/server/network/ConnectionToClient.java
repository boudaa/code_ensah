package com.ensah.chat.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ensah.chat.dataobjects.CommunicationMessage;
import com.ensah.chat.dataobjects.ReceivedMessageQueue;
import com.ensah.chat.dataobjects.ReceivedMessage;
import com.ensah.chat.dataobjects.MessageToSendQueue;

/**
 * Cette encapsule les informations liées à une connexion d'un client ainsi que
 * les messages envoyés par ce clients. Elle implémente également les threads de
 * communication avec le client
 * 
 * @author boudaa
 *
 */
public class ConnectionToClient {

	/** identifiant du client */
	private int idClient;

	/** soket qui encapsule la connexion réseau avec le client */
	private Socket connection;

	/** file d'attente des messages à envoyer */
	private final MessageToSendQueue outgoingMessages;

	/**
	 * thread copie les messages de la file d'attente pour les écrire dans flux de
	 * sortie afin q'ils soit envoyés au client
	 */
	private Thread sendThread;

	/**
	 * Thread qui lit depuis le flux d'entrée et ajoute les données dans la file
	 * d'attente des messages
	 */
	private volatile Thread receiveThread;

	/** flux d'entrée (pour la reception) assoicé à la socket associé au client */
	private ObjectInputStream in;

	/** flux de sortie (pour l'envoi) assoicé à la socket associé au client */
	private ObjectOutputStream out;

	public ConnectionToClient(int id, Socket clientSocket) throws IOException {

		// Initialisations
		this.idClient = id;
		this.connection = clientSocket;
		outgoingMessages = new MessageToSendQueue();

		// On obtient les références sur les flu d'entrée et sortie associées à la
		// socket
		out = new ObjectOutputStream(connection.getOutputStream());
		in = new ObjectInputStream(connection.getInputStream());

		// Création du thread d'envoi
		sendThread = new SendThread();

		// définir ce thread comme demon
		sendThread.setDaemon(true);

		// démarrer ce thread
		sendThread.start();

	}

	/* classe interne qui définit le thread d'envoi **/
	private class SendThread extends Thread {
		public void run() {

			try {

				// Envoyer l'id au client en écrivant dans le flux de sortie
				synchronized (ConnectionToClient.this) {
					out.writeObject(getIdClient());
				}

				// On dérmarre le thread de reception
				receiveThread = new ReceiveThread();
				// définir ce thread comme demon
				receiveThread.setDaemon(true);
				// démarrer
				receiveThread.start();

				// Prendre les messages de la file outgoingMessages et les envoyer
				while (true) {

					// cette instruction est bloquante : rester bloqué ici si la file et vide
					Object message = outgoingMessages.takeMessage();

					// écrire le message obtenu de la file dans le flux de sortie
					out.writeObject(message);

					// pour forcer l'envoi
					out.flush();

				}

			} catch (Exception e) {

				throw new ServerNetworkException(e);
			}

		}
	}

	/* classe interne qui définit le thread de reception **/
	private class ReceiveThread extends Thread {
		public void run() {
			try {

				// boucler à l'infinie
				while (true) {

					// lire les données du flux d'entrée
					// données
					Object message = in.readObject();

					// créer un objet message
					ReceivedMessage msg = new ReceivedMessage();
					// lui associer l'objet connection qui encapsule les informations du client
					msg.setClientConnexion(ConnectionToClient.this);
					msg.setMessage(message);
					// Obtenir l'unique file d'attente des messages reçu puis enfiler ce message
					// dans cette file
					ReceivedMessageQueue.getIncomingMessageQueue().putMessageInTheQueue(msg);

				}
			} catch (Exception e) {
				throw new ServerNetworkException(e);

			}

		}

	}

	/**
	 * Permet d'ajouter un message à la file d'envoir
	 * 
	 * @param obj
	 */
	void send(CommunicationMessage obj) {

		if (obj != null) {
			outgoingMessages.putMessageInTheQueue(obj);
		}
	}

	@Override
	public String toString() {
		return "Client [id=" + idClient + "]";
	}

	public void setId(int id) {
		this.idClient = id;
	}

	public Socket getClientSocket() {
		return connection;
	}

	public void setClientSocket(Socket clientSocket) {
		this.connection = clientSocket;
	}

	int getIdClient() {
		return idClient;
	}
}
