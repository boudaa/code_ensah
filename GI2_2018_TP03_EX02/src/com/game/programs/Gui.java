package com.game.programs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.game.Echiquier;
import com.game.Piece;
import com.game.exceptions.ImpossibleDeplacementException;

public class Gui extends JFrame {

	private JPanel panEchequier = new JPanel();

	private JPanel panCommande = new JPanel();

	private JLabel lab = new JLabel();

	private JButton executerCommande = new JButton("Executer Commander");
	private JTextField commandeText = new JTextField(20);

	// initialiser l'échequier
	public static Echiquier echequier = new Echiquier();

	public void upadte() {
		lab.setText(echequier.getEchequierState());
	}

	public void play() {

		String commande = commandeText.getText();

		// Véifier que la commande a été correctement saisie
		Matcher m = Pattern.compile("^([A,B,C]) (a|b|t|bt|dta|dtb|dba|dbb)$").matcher(commande);
		boolean correctMove = false;

		if (m.matches()) {

			String pieceString = m.group(1);
			String directionString = m.group(2);
			int direction = 0;

			if ("a".equals(directionString)) {
				direction = Piece.ADVANCE;
			} else if ("b".equals(directionString)) {
				direction = Piece.BACK;
			} else if ("t".equals(directionString)) {
				direction = Piece.TOP;
			} else if ("bt".equals(directionString)) {
				direction = Piece.BOTTOM;
			} else if ("dta".equals(directionString)) {
				direction = Piece.DIAG_TOP_ADVANCE;
			} else if ("dtb".equals(directionString)) {
				direction = Piece.DIAG_TOP_BACK;
			} else if ("dba".equals(directionString)) {
				direction = Piece.DIAG_BOTTOM_BACK;
			} else if ("dbb".equals(directionString)) {
				direction = Piece.DIAG_BOTTOM_BACK;
			}

			Piece piece = echequier.getPieceWithColor(echequier.getTour(), pieceString);
			if (piece != null) {
				try {
					piece.seDeplacer(direction, 1);
					correctMove = true;
				} catch (ImpossibleDeplacementException e) {
					correctMove = false;
					System.out.println("Déplacement impossible");
				}

			} else {
				System.out.println("La piece n'existe plus");

			}

			upadte();

		} else if ("exit".equals(commande)) {
			System.exit(0);
		}

		else {
			System.out.println("commande incorrecte");
		}
		if (correctMove) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {

					}
					echequier.randomDeplacement();

					upadte();

				}
			}).start();
		}
	}

	public Gui() {

		setTitle("Game");
		setSize(700, 500);

		add(panEchequier);
		panEchequier.add(lab);

		panCommande.add(commandeText);
		panCommande.add(executerCommande);

		add(panCommande, BorderLayout.SOUTH);

		executerCommande.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				play();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		upadte();
		setVisible(true);

	}

	public static void main(String[] args) {

		new Gui();
	}

}
