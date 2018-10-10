package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.exceptions.ImpossibleDeplacementException;

/**
 * Représente une pièce
 * 
 * @author Boudaa
 *
 */
public abstract class Piece {

	// constantes qui définit les déplacements
	public static final int ADVANCE = 1;
	public static final int BACK = 2;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;
	public static final int DIAG_TOP_ADVANCE = 5;
	public static final int DIAG_TOP_BACK = 6;
	public static final int DIAG_BOTTOM_ADVANCE = 7;
	public static final int DIAG_BOTTOM_BACK = 8;

	// Directions possibles
	public static final int[] DIRECTIONS = { ADVANCE, BACK, TOP, BOTTOM, DIAG_TOP_ADVANCE, DIAG_TOP_BACK,
			DIAG_BOTTOM_ADVANCE, DIAG_BOTTOM_BACK };

	/** Réference l'échequier */
	protected Echiquier echquier;

	/** position de la piece dans l'echequier */
	protected Point position;

	/** Couleur d'une piece */
	protected int couleur;

	/** Constructeur */
	public Piece(Point p, int c, Echiquier ech) {
		position = p;
		couleur = c;
		echquier = ech;
	}

	/**
	 * chaque piece à sa propre façon de se déplacer cette méthode est abstraite et
	 * sera donc implémentée différement dans chaque type de piece
	 */
	public abstract void isPossibleMove(Point pos) throws ImpossibleDeplacementException;

	/**
	 * chque piece a sa propre facon pour calculer la force c'est pour cette raison
	 * cette méthode est déclarée abstraite
	 */
	public abstract int getPower();

	/** permet d'avoir une representation sous forme d'une chaine de caracteres */
	public abstract String getStringRepresentation();

	/**
	 * Cette méthode permet de déplacer une piece
	 * 
	 * @param direction
	 * @param nbrCase
	 * @throws ImpossibleDeplacementException
	 */
	public void seDeplacer(int direction, int nbrCase) throws ImpossibleDeplacementException {

		// on obtient la position apres le déplacement, si ce déplacement est possible
		// pour la piece appelante
		// cette méthode fait un appel à la méthode isPossibleMove qui est propre à
		// chaque type de piece
		Point tempPos = getPositionAfterMoveIfPossible(direction, nbrCase);

		// Si on arrive à ce point alors le déplacement est possible et il reste à
		// éliminer la piece adversaire si elle est dans tempPos

		Piece p = echquier.getPieceAt(tempPos);

		if (echquier.getPieceAt(tempPos) != null) {
			echquier.removePiece(p);
		}

		// mettre à jour la position de la piece
		position = tempPos;

		echquier.inverserTour();

	}

	/**
	 * Permet de détérminer tous les déplacements possibles d'une pièce
	 * 
	 * @param p
	 * @param couleur
	 * @return
	 */
	public List<Point> getPossibleMoves() {

		List<Point> moves = new ArrayList<Point>();

		for (int it : DIRECTIONS) {
			try {
				moves.add(getPositionAfterMoveIfPossible(it, 1));

			} catch (ImpossibleDeplacementException ex) {
				// on ignore volentairement cette exception
				// si position illégale on ajoute pas le dépalcement dans la liste moves
			}
		}

		return moves;

	}

	/**
	 * Cette méthode permet d'avoir la nouvelle position d'une piece apres son
	 * déplacement, cette méthode vérifie que ce deplacement est possible @see
	 * isPossibleMove
	 * 
	 * @param direction
	 *            le sens de déplacement
	 * 
	 * @param nbrCase
	 *            nombre de cases
	 * 
	 * @return
	 * @throws ImpossibleDeplacementException
	 */
	private Point getPositionAfterMoveIfPossible(int direction, int nbrCase) throws ImpossibleDeplacementException {
		Point newpos = null;

		if (direction == ADVANCE) {

			// si la pièce est noir
			if (couleur == Color.NOIR) {
				newpos = new Point(position.getX(), position.getY() + nbrCase);
			} else if (couleur == Color.BLANC) {
				// pièce blanche
				newpos = new Point(position.getX(), position.getY() - nbrCase);
			}
		} else if (direction == BACK) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX(), position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX(), position.getY() - nbrCase);
			}

		} else if (direction == TOP) {

			newpos = new Point(position.getX() - nbrCase, position.getY());

		} else if (direction == BOTTOM) {

			newpos = new Point(position.getX() + nbrCase, position.getY());

		} else if (direction == DIAG_BOTTOM_ADVANCE) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() + nbrCase, position.getY() - nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() + nbrCase, position.getY() + nbrCase);
			}

		} else if (direction == DIAG_BOTTOM_BACK) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() + nbrCase, position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() + nbrCase, position.getY() - nbrCase);
			}

		} else if (direction == DIAG_TOP_ADVANCE) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() - nbrCase, position.getY() - nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() - nbrCase, position.getY() + nbrCase);
			}

		} else if (direction == DIAG_TOP_BACK) {
			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() - nbrCase, position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() - nbrCase, position.getY() - nbrCase);
			}

		}

		if (!Echiquier.isPosInEchequier(newpos)) {
			throw new ImpossibleDeplacementException();
		}

		Piece p = echquier.getPieceAt(newpos);

		if (p != null && (p.getCouleur() == couleur || (p.getCouleur() != couleur && getPower() < p.getPower())))

		{
			throw new ImpossibleDeplacementException();
		}

		// vérification des regles de déplacement de la piece
		isPossibleMove(newpos);

		// la nouvelle position
		return newpos;
	}

	/**
	 * Cette méthode permet de générer un déplacement aléatoire pour une piece
	 * donnée, elle prend en compte les regles de deplacement de chaque piece
	 */
	public void randomDeplacement() {

		// On obtient les déplacements possibles d'une piece, en prenant en compte les
		// regles de déplacement générales et les regles de chaque piece
		List<Point> possibleMoves = getPossibleMoves();

		// Un entier par hazard dans l'intervale [0,possibleMoves.size()-1]
		int randomIndex = new Random().nextInt(possibleMoves.size());

		// On traite le cas ou la case contient une piece adverse on l'élémine de
		// l'echequier
		Point temp = possibleMoves.get(randomIndex);
		Piece p = echquier.getPieceAt(temp);
		// il contient une piece adverse
		if (p != null) {
			echquier.removePiece(p);
		}

		// on change la case de la piece
		position = temp;

		// On passe la main à l'autre joueur
		echquier.inverserTour();

	}

	public int getCouleur() {
		return couleur;
	}

	public Point getPosition() {
		return position;
	}

	public String toString() {
		return "Piece [echquier=" + echquier + ", position=" + position + ", couleur=" + couleur + "]";

	}

}
