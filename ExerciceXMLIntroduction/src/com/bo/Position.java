package com.bo;

public class Position {

	private int x;

	private int y;

	private int t;

	public Position() {

	}

	public Position(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", t=" + t + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

}
