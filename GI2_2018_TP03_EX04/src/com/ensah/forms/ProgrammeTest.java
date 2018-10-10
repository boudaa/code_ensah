package com.ensah.forms;

import java.io.IOException;
import java.util.List;

public class ProgrammeTest {

	public static void main(String[] args) {

		DocumentGraphique dg = new DocumentGraphique();

		// Initialisation depuis un fichier
		try {
			dg.initDocWithCubesFromFile("testForgi2.txt");

			dg.showDoc();

		} catch (IOException e) {

			System.out.println("Erreur de lecteure du fichier");

		}

		// On ajoute dans ce documents d'autres formes

		Brique b1 = new Brique(new Point3D(0, 1, 1), 12, 1, 1, 3);
		dg.addForme(b1);

		Boule bl1 = new Boule(new Point3D(1, 2, 1), 1, 1);

		dg.addForme(bl1);

		// On affiche le contenu de Doc Graphique

		dg.showDoc();

		// On récupère puis on affiche les cubes du document graphique
		List<Cube> cubes = dg.getAllCubes();

		for (Cube it : cubes) {
			System.out.println(it);
		}

		// On affiche le poids total

		double poid = dg.poidsTotal();

		System.out.println("Poid total =" + poid);

	}

}
