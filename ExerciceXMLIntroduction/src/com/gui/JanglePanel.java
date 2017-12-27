package com.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bo.Animal;
import com.bo.Position;
import com.xml.XMLReader;

/**
 * Paneau principal de l'application
 * 
 * @author Tarik BOUDAA
 *
 */
public class JanglePanel extends JPanel {

	private Animal animal;

	private XMLReader xmlReader;

	private List<Animal> animals;

	public int deplacer() {

		int start = animal.currentPos().getT();

		animal.deplacer();

		int end = animal.currentPos().getT();

		return end - start;

	}
	
	public void reinitAll(){
		animals = xmlReader.getAnimals();
	}

	public JanglePanel() {

		xmlReader = new XMLReader();

		animals = xmlReader.getAnimals();

		// animal par défaut
		animal = animals.get(0);

	}

	public void choisirAnimal(int idAnimal) {
		animal = animals.get(idAnimal);
	}

	public void paintComponent(Graphics g) {

		Image img;

		// position en cours de l'animal
		Position animalPosition = animal.currentPos();

		// Lire l'image de l'animal
		try {
			img = ImageIO.read(animal.getPic());

			// deciner l'animal à sa position 
			g.drawImage(img, animalPosition.getX(), animalPosition.getY(), 50,
					50, this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
