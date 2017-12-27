package com.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import com.bo.Animal;
import com.bo.Position;

/**
 * Permet de parser le fichier XML présentant les animaux et leurs déplacements
 * 
 * @author Tarik BOUDAA
 *
 */
public class XMLReader {

	private final DocumentBuilderFactory factory = DocumentBuilderFactory
			.newInstance();

	private DocumentBuilder builder;
	private Document document;
	private Element racine;

	/**
	 * Constructeur qui initialise le parseur DOM et récupère la racine du
	 * document
	 */
	public XMLReader() {

		try {

			builder = factory.newDocumentBuilder();

			document = builder.parse(new File("deplacement.xml"));

			racine = document.getDocumentElement();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Construit une liste d'animaux à partir de l'arbre DOM
	 * 
	 * @return liste des animaux
	 */
	public List<Animal> getAnimals() {

		// Pour stocker la liste des animaux
		List<Animal> animals = new ArrayList<Animal>();

		// les animaux sont dans cette liste
		NodeList nodList = racine.getChildNodes();

		Animal animal;
		Node listNodElm;
		for (int i = 0; i < nodList.getLength(); i++) {

			listNodElm = nodList.item(i);

			if (listNodElm.getNodeType() == Node.ELEMENT_NODE) {

				int id = Integer.parseInt(((Element) listNodElm)
						.getAttribute("id"));

				animal = new Animal(id,
						((Element) listNodElm).getAttribute("nom"),
						((Element) listNodElm).getAttribute("pic"));

				// On récupère les positions
				NodeList positions = listNodElm.getChildNodes();

				for (int j = 0; j < positions.getLength(); j++) {

					if (positions.item(j).getNodeType() == Node.ELEMENT_NODE) {

						int x = Integer.parseInt(((Element) positions.item(j))
								.getAttribute("x"));
						int y = Integer.parseInt(((Element) positions.item(j))
								.getAttribute("y"));
						int t = Integer.parseInt(((Element) positions.item(j))
								.getAttribute("t"));

						Position p = new Position(x, y, t);

						animal.addPosition(p);

					}

				}
				animals.add(animal);

			}

		}

		return animals;

	}

}
