package com.game.programs;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.Echiquier;
import com.game.Piece;
import com.game.exceptions.ImpossibleDeplacementException;

public class GameLauncher {

	// initialiser l'échequier
	public static Echiquier echequier = new Echiquier();

	public static void playWithMachine() throws Exception {
		Scanner s = new Scanner(System.in);

		echequier.showEchequier();

		while (true) {

			// Lire la commande au clavier
			String commande = s.nextLine();
			boolean correctMove = false;

			// Véifier que la commande a été correctement saisie
			Matcher m = Pattern.compile("^([A,B,C]) (a|b|t|bt|dta|dtb|dba|dbb)$").matcher(commande);

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

			} else if ("exit".equals(commande)) {
				System.exit(0);
			}

			else {
				System.out.println("commande incorrecte");
			}
			echequier.showEchequier();
			if (correctMove) {
				// Attendre un peu
				Thread.sleep(4000);
				echequier.randomDeplacement();

				echequier.showEchequier();
			}
		}

	}

	public static void playWithHuman() throws Exception {
		Scanner s = new Scanner(System.in);

		echequier.showEchequier();

		while (true) {

			// Lire la commande au clavier
			String commande = s.nextLine();

			// Véifier que la commande a été correctement saisie
			Matcher m = Pattern.compile("^([A,B,C]) (a|b|t|bt|dta|dtb|dba|dbb)$").matcher(commande);

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

				try {

					Piece piece = echequier.getPieceWithColor(echequier.getTour(), pieceString);
					if (piece != null) {
						piece.seDeplacer(direction, 1);

					} else {
						System.out.println("La piece n'existe plus");

					}
				} catch (ImpossibleDeplacementException e) {
					System.out.println("Déplacement impossible");
				}

			} else if ("exit".equals(commande)) {
				System.exit(0);
			}

			else {
				System.out.println("commande incorrecte");
			}

			echequier.showEchequier();
		}

	}

	public static void playRadomGame() throws Exception {
		Scanner s = new Scanner(System.in);

		echequier.showEchequier();

		while (true) {
			Thread.sleep(4000);
			echequier.randomDeplacement();

			echequier.showEchequier();

			// Attendre un peu
			Thread.sleep(4000);
			echequier.randomDeplacement();

			echequier.showEchequier();
		}

	}

	public static int showMenu() {

		Scanner s = new Scanner(System.in);

		System.out.println("Entrer votre choix");
		System.out.println("1- Machine contre Machine");
		System.out.println("2- Joueur contre Machine");
		System.out.println("3- Joueur contre Joueur");
		return s.nextInt();

	}

	public static void main(String[] args) throws Exception {

		int choix = showMenu();
		switch (choix) {

		case 1:
			playRadomGame();
			break;
		case 2:
			playWithMachine();
			break;
		case 3:
			playWithHuman();

			break;
		}

	}

}
