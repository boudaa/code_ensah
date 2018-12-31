package com.ensah.chat.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {

	private JPanel centerPan = new JPanel();
	private JPanel buttonPan = new JPanel();
	private JButton sendBtn = new JButton("Envoyer");
	private JTextField messageToSend = new JTextField(20);
	private JTextArea roomScreen = new JTextArea(100, 100);

	public ChatPanel() {

		setLayout(new BorderLayout());
		add(roomScreen);

		buttonPan.add(messageToSend);

		buttonPan.add(sendBtn);

		add(buttonPan, BorderLayout.SOUTH);
	}

	public void addActionToSendBtn(ActionListener action) {
		sendBtn.addActionListener(action);
	}

	public JPanel getCenterPan() {
		return centerPan;
	}

	public void setCenterPan(JPanel centerPan) {
		this.centerPan = centerPan;
	}

	public JPanel getButtonPan() {
		return buttonPan;
	}

	public void setButtonPan(JPanel buttonPan) {
		this.buttonPan = buttonPan;
	}

	public JButton getSendBtn() {
		return sendBtn;
	}

	public void setSendBtn(JButton sendBtn) {
		this.sendBtn = sendBtn;
	}

	public String getMsgToSend() {
		return messageToSend.getText();
	}

	public void setMessageToSend(String pText) {
		messageToSend.setText(pText);
	}

	public String getRoomScreenMsg() {
		return roomScreen.getText();
	}

	public void setRoomScreenMsg(String pText) {
		roomScreen.setText(pText);
	}

}
