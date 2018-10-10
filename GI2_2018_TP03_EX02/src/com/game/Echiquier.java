package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represente l'echequier du jeu
 * 
 * @author BOUDAA
 *
 */
public class Echiquier {

	/** Liste des pièces */
	private List<Piece> pieces = new ArrayList<Piece>();

	/** Le score du joueur noir */
	private int scoreNoir; // score jour NOIR

	/** Le score du joueur blanc */
	private int scoreBlanc; // score jour BLANC

	/** permet de savoir le joueur qui va jouer */
	private int tour = Color.BLANC;

	/**
	 * Initialisation de l'échequier
	 */
	public Echiquier() {

		// Création des pièces pour le premier joueur
		Piece pab = new PieceA(new Point(0, 6), Color.BLANC, this);
		Piece pbb = new PieceB(new Point(1, 6), Color.BLANC, this);
		Piece pcb = new PieceC(new Point(2, 6), Color.BLANC, this);

		// Création des pièces pour le deuxième joueur
		Piece pan = new PieceA(new Point(0, 0), Color.NOIR, this);
		Piece pbn = new PieceB(new Point(1, 0), Color.NOIR, this);
		Piece pcn = new PieceC(new Point(2, 0), Color.NOIR, this);

		pieces.add(pab);
		pieces.add(pbb);
		pieces.add(pcb);
		pieces.add(pan);
		pieces.add(pbn);
		pieces.add(pcn);

	}

	/** passe la main à l'autre jour */
	public void inverserTour() {
		tour = -tour;
	}

	/**
	 * Permet de déplacer aléatoirement l'une des piece passées en parametre
	 * 
	 * @param lPieces
	 */
	public void randomDeplacement(List<Piece> lPieces) {

		Piece p = lPieces.get(new Random().nextInt(lPieces.size()));
		p.randomDeplacement();

	}

	/**
	 * Permet de déplacer aléatoirement l'une des pieces du joueur ayant la main
	 */
	public void randomDeplacement() {

		List<Piece> lPieces = getPiecesWithColor(tour);

		Piece p = lPieces.get(new Random().nextInt(lPieces.size()));
		p.randomDeplacement();

	}

	/**
	 * Permet de déplacer aléatoirement l'une des pieces du joueur dont la couleur
	 * est passée en paramètre
	 */
	public void randomDeplacement(int pTour) {

		List<Piece> lPieces = getPiecesWithColor(pTour);

		Piece p = lPieces.get(new Random().nextInt(lPieces.size()));
		p.randomDeplacement();

	}

	/**
	 * Permet de lancer une partie de jeu d'une façon aléatoire
	 */
	public void randomGame() {
		showEchequier();

		while (true) {

			List<Piece> lPieces = getPiecesWithColor(tour);

			if (lPieces.size() == 0) {
				break;
			}

			randomDeplacement(lPieces);

			// attente de 3 secondes
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			showEchequier();

		}

	}

	/**
	 * Retourne la pièce à la position p
	 * 
	 * @param p
	 * @return
	 */
	public Piece getPieceAt(Point p) {

		for (Piece piece : pieces) {

			if (piece.getPosition().getX() == p.getX() && piece.getPosition().getY() == p.getY())

				return piece;

		}

		return null;

	}

	/**
	 * Permet d'éléminer une pièce de l'echequier
	 * 
	 * @param p
	 *            pièce
	 */
	public void removePiece(Piece p) {

		// Mise à jour du score
		if (p.getCouleur() == Color.NOIR) {
			scoreBlanc = +p.getPower();
		} else if (p.getCouleur() == Color.BLANC) {
			scoreNoir = +p.getPower();

		}

		// Ici la pièce ayant meme référence sera supprimée (car nous n'avons
		// pas redéfinit equals, ici on a supposer que l'appelant a la référence
		// de la pièce à supprimer)
		pieces.remove(p);
	}

	/**
	 * Cette méthode permet de vérifier si un point est à l'interieur de l'
	 * echequier
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isPosInEchequier(Point p) {

		return (p.getX() >= 0 && p.getX() <= 2) && (p.getY() >= 0 && p.getY() <= 6);

	}

	/**
	 * Permet de savoir si une case est grise ou non
	 * 
	 * @param p
	 * @param couleur
	 * @return
	 */
	public static boolean isCaseGrise(Point p) {

		return ((p.getX() == 0 || p.getX() == 2) && p.getY() % 2 == 0) && (p.getX() == 1 && p.getY() % 2 != 0);

	}

	/**
	 * Affiche l'échequier
	 */
	public void showEchequier() {

		System.out.println("Score Noire  : " + scoreNoir);
		System.out.println("Score Blanc  : " + scoreBlanc);

		for (int i = 0; i < 7; i++) {

			System.out.println("-------------------------------------------------");

			for (int j = 0; j < 3; j++) {

				Piece p = getPieceAt(new Point(j, i));

				if (p == null)
					System.out.print("|		");
				else {
					System.out.print("|	      " + p.getStringRepresentation());
				}

			}
			System.out.println("|");

		}

		System.out.println("-------------------------------------------------");

	}
	public String getEchequierState() {

		String tab="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		StringBuffer sb= new StringBuffer();
		sb.append("<html><br/>");
		
		sb.append("Score Noire  : " + scoreNoir+"<br/>");
		sb.append("Score Blanc  : " + scoreBlanc+"<br/>");

		for (int i = 0; i < 7; i++) {

			sb.append("---------------------<br/>");

			for (int j = 0; j < 3; j++) {

				Piece p = getPieceAt(new Point(j, i));

				if (p == null)
					sb.append("|"+tab);
				else {
					sb.append("|&nbsp;" + p.getStringRepresentation()+"&nbsp;");
				}

			}
			sb.append("|<br/>");

		}

		sb.append("---------------------<br/>");
		sb.append("<br/></html>");
		return sb.toString();

	}
	public List<Piece> getPieces() {
		return pieces;
	}

	/** retourne la liste des pieces ayant la couleur passée comme paramètre */
	public List<Piece> getPiecesWithColor(int col) {
		List<Piece> lPieces = new ArrayList<Piece>();
		for (Piece it : pieces) {

			if (it.getCouleur() == col) {
				lPieces.add(it);
			}
		}
		return lPieces;
	}

	/** retourne la piece ayant la couleur et le nom passés comme paramètres */
	public Piece getPieceWithColor(int col, String label) {
		List<Piece> lPieces = getPiecesWithColor(col);

		for (Piece it : lPieces) {

			if (it instanceof PieceA && "A".equals(label)) {
				return it;
			}

			if (it instanceof PieceB && "B".equals(label)) {
				return it;
			}

			if (it instanceof PieceC && "C".equals(label)) {
				return it;
			}
		}

		return null;
	}

	public int getTour() {
		return tour;
	}

}
