package com.game;

import com.game.exceptions.ImpossibleDeplacementException;

public class PieceC extends Piece {

	public PieceC(Point p, int c, Echiquier ech) {
		super(p, c, ech);
	}

	public int getPower() {
		return position.getY() * position.getX();
	}

	public String getStringRepresentation() {
		if (couleur == Color.NOIR)
			return "CN";
		else
			return "CB";
	}

	public void isPossibleMove(Point pos) throws ImpossibleDeplacementException {
		if (pos.getX() == pos.getY()) {
			throw new ImpossibleDeplacementException();
		}
	}
}
