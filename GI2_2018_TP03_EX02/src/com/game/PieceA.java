package com.game;

import com.game.exceptions.ImpossibleDeplacementException;

public class PieceA extends Piece {

	public PieceA(Point p, int c, Echiquier ech) {
		super(p, c, ech);
	}

	public int getPower() {
		return position.getY() * position.getY();
	}

	public String getStringRepresentation() {

		if (couleur == Color.NOIR)
			return "AN";
		else
			return "AB";
	}

	public void isPossibleMove(Point pos) throws ImpossibleDeplacementException {
		// Pas de conditions particulière actullement
	}

}
