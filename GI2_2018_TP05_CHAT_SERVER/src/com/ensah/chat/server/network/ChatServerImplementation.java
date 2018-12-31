package com.ensah.chat.server.network;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.ensah.chat.dataobjects.CommunicationMessage;
import com.ensah.chat.dataobjects.ReceivedMessageQueue;
import com.ensah.chat.dataobjects.ReceivedMessage;

/**
 * Serveur de chat
 * 
 * @author boudaa
 *
 */
public class ChatServerImplementation {

	/** permet de stocker les connections des clients */
	private List<ConnectionToClient> clientToConnectionList;

	/** permet de stocker les messages reçu */
	private ReceivedMessageQueue incomingMessageQueue;

	/** permet d'écouteur les demandes de connexion des clients */
	private ServerSocket serverSockets;

	/** compteur des identifiants affectés aux clients */
	private volatile int nextClientID = 1;

	/** port sur lequel écoute le serveur */
	private int port;

	/**
	 * Méthode permettant de démarrer le serveur
	 * 
	 * @throws IOException
	 */
	public void serverStart(int pPort) throws IOException {

		// initialisation du port
		port = pPort;

		// initialisations
		clientToConnectionList = new CopyOnWriteArrayList<ConnectionToClient>();
		incomingMessageQueue = ReceivedMessageQueue.getIncomingMessageQueue();

		// Créer la socket serveur
		serverSockets = new ServerSocket(port);

		// On dermarre un thread qui va s'occuper d'écouter les demandes des clients
		// Cette tache d'écoute est bloquante, ainsi elle est affectée à un thread
		Thread serverThread = new Thread(new Runnable() {

			public void run() {
				try {

					while (true) {

						System.out.println("Le Serveur est à l'écoute des connections sur le port :" + port + " ....");

						// écoute des demandes de connexions; cette instruction reste en attente jusqu'à
						// reception d'une demande de connexion
						Socket soc = serverSockets.accept();

						// si on recoit une demande de connexion
						// (on peut recevoir plusieurs en meme temps ==> donc on synchronise ici)
						synchronized (ChatServerImplementation.this) {
							nextClientID++;
							ConnectionToClient client = new ConnectionToClient(nextClientID, soc);
							clientToConnectionList.add(client);
						}
					}
				} catch (Exception ex) {

					throw new ServerNetworkException(ex);
				}
			}
		});

		// On demarre ce thread
		serverThread.start();

		// On créer un thread qui s'occupera du traitement des messages reçu
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {
						// On prend un message de la file d'attente des messages reçus
						ReceivedMessage msg = incomingMessageQueue.takeMessage();

						// On le dispatch sur tous les clients connectés
						sendToAll(msg.getClientConnection(), msg.getMessage());

					} catch (Exception ex) {
						throw new ServerNetworkException(ex);

					}
				}

			}
		});

		// On dermarre ce thread
		th.start();


	}

	/**
	 * Permet d'envoyer les messages à tous les clients connectés
	 * 
	 * @param fromConnection
	 * @param message
	 */
	synchronized private void sendToAll(ConnectionToClient fromConnection, Object message) {

		CommunicationMessage fmsg = new CommunicationMessage(fromConnection.getIdClient(), message);

		// Ajouter ce message dans les lists de messages à envoyer
		for (ConnectionToClient clientConnection : clientToConnectionList) {

			// Ajouter le message dans la liste d'envoit pour ce client
			clientConnection.send(fmsg);
		}

	}

}
