package com.ensah;

public class PointCol {

	private int i;

	private int j;

	private int value;

	public PointCol() {
	}

	public PointCol(int i, int j, int value) {
		this.i = i;
		this.j = j;
		this.value = value;
	}

	public String toString() {
		return "PointCol [i=" + i + ", j=" + j + ", value=" + value + "]";
	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		PointCol other = (PointCol) obj;

		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
