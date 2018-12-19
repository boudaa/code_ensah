package com.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabledTextField extends JPanel {

	private JLabel lab;

	private JTextField text;

	public LabledTextField(String pLabel) {

		lab = new JLabel(pLabel);
		text = new JTextField(10);
		
		add(lab);
		add(text);
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String pText) {
		text.setText(pText);
	}

}
