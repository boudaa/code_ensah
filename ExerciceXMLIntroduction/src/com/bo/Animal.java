package com.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Animal {

	private int id;

	private String nom;

	private File pic;

	private List<Position> positions = new ArrayList<Position>();

	private int currentPos;

	public Animal() {
	}

	public Animal(int id, String nom, String pic) {
		this.id = id;
		this.nom = nom;
		this.pic = new File(pic);
	}

	public Animal(String nom, String pic) {
		this.nom = nom;
		this.pic = new File(pic);
	}

	public void addPosition(Position p) {
		positions.add(p);
	}

	public boolean notEnd() {
		return (currentPos < positions.size() - 1);
	}

	public Position deplacer() {

		if (notEnd()) {
			currentPos++;
		}

		return currentPos();
	}

	public Position currentPos() {
		return positions.get(currentPos);
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nom=" + nom + ", pic=" + pic
				+ ", currentPos=" + currentPos + ", positions=" + positions
				+ "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

}
