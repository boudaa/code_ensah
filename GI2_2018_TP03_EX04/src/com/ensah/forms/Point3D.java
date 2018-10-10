package com.ensah.forms;

public class Point3D {

	private double x;

	private double y;

	private double z;

	public Point3D() {
	}

	public Point3D(double pX, double pY, double pZ) {

		x = pX;
		y = pY;
		z = pZ;
	}

	public String toString() {

		return "(" + x + "," + y + "," + z + ")";

	}

	public void deplacer(double dx, double dy, double dz) {

		x += dx;
		y += dy;
		z += dz;

	}

	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (obj == null)
			return false;

		if (obj.getClass() != Point3D.class)
			return false;

		Point3D point = (Point3D) obj;

		return (x == point.x && y == point.y && z == point.z);

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
