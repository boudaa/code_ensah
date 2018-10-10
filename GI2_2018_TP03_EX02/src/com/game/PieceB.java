package com.game;

import com.game.exceptions.ImpossibleDeplacementException;

public class PieceB extends Piece {

	public PieceB(Point p, int c, Echiquier ech) {
		super(p, c, ech);
	}

	public int getPower() {
		if (position.getX() % 2 == 0)
			return 1;

		return 0;
	}

	public String getStringRepresentation() {
		if (couleur == Color.NOIR)
			return "BN";
		else
			return "BB";
	}

	public void isPossibleMove(Point pos) throws ImpossibleDeplacementException {
		if (Echiquier.isCaseGrise(pos)) {
			throw new ImpossibleDeplacementException();
		}
	}

}
