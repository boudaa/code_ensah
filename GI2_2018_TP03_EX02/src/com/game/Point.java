package com.game;

public class Point {

	private int x;

	private int y;

	public Point() {
	}

	public Point(int px, int py) {

		x = px;
		y = py;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "<"+x+","+y+">";
	}
	

}
