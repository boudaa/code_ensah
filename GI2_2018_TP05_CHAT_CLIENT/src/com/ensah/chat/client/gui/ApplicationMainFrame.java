package com.ensah.chat.client.gui;

import java.net.*;

import javax.swing.JFrame;

import com.ensah.chat.client.gui.ChatPanel;
import com.ensah.chat.dataobjects.CommunicationMessage;
import com.network.ConnectionToServer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ApplicationMainFrame extends JFrame implements Observer {

	private String hote = "127.0.0.1";
	private int port = 2001;

	private ChatPanel panelChat = new ChatPanel();

	private ConnectionToServer connectionToServer;

	public static void main(String[] args) throws Exception {

		EventQueue.invokeAndWait(new Runnable() {

			public void run() {
				try {
					new ApplicationMainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public ApplicationMainFrame() throws UnknownHostException, ClassNotFoundException, IOException {

		// création d'une connextion au serveur
		connectionToServer = new ConnectionToServer(hote, port);

		buildAndLaunchGui();

	}

	private void buildAndLaunchGui() {

		setTitle("ENSAH LAN CHAT APPLICATION");
		add(panelChat);

		// écouter l'observable
		connectionToServer.addObserver(this);

		// Traitement de l'action sur le bouton envoyer
		panelChat.addActionToSendBtn(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// On appel la méthode send
				send(panelChat.getMsgToSend());
				panelChat.setMessageToSend("");

			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}

	/** permet d'envoyer les messages */
	public void send(Object message) {

		connectionToServer.send(message);
	}

	public void update(Object o) {

		CommunicationMessage message = (CommunicationMessage) o;
		StringBuffer sb = new StringBuffer(panelChat.getRoomScreenMsg());
		sb.append("Sender ");
		sb.append(message.senderID + " : ");
		sb.append(message.message + " \n ");

		panelChat.setRoomScreenMsg(sb.toString());

	}

}
