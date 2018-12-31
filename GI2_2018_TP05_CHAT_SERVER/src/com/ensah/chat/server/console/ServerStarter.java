package com.ensah.chat.server.console;

import com.ensah.chat.server.network.ChatServerImplementation;

public class ServerStarter {

	public static void main(String[] args) {

		try {
			// Port du serveur
			int port = 2001;

			new ChatServerImplementation().serverStart(port);

		} catch (Exception ex) {

			System.out.println("Erreur de démarrage du Serveur à cause de l'erreur " + ex);
		}

	}

}
