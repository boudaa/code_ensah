package com.ensah.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Présente un document graphique
 * 
 * @author boudaa
 *
 */
public class DocumentGraphique {

	/** pour traduire le lien de composition entre cette classe et les formes */
	private List<Forme> formes = new ArrayList<Forme>();

	/**
	 * Permet d'ajouter une forme au document graphique
	 * 
	 * @param forme
	 */
	public void addForme(Forme forme) {

		formes.add(forme);
	}

	/**
	 * Permet de supprimer une forme
	 * 
	 * @param pos
	 */
	public void removeForme(int pos) {

		formes.remove(pos);
	}

	/**
	 * Permet d'avoir la liste des cubes dans document graphique
	 * 
	 * @return
	 */
	public List<Cube> getAllCubes() {

		// liste pour y stocker les cubes
		List<Cube> cubes = new ArrayList<Cube>();

		for (Forme it : formes) {

			// s'il s'agit d'un cube nous allons l'ajouter à la liste

			if (it instanceof Cube) {

				cubes.add((Cube) it);

			}

			// On peut aussi utiliser getClass
			// if (it.getClass() == Cube.class) {
			//
			//
			//
			//
			// }
		}

		return cubes;

	}

	/**
	 * Méthode affichant le contenu du document
	 */
	public void showDoc() {
		System.out.println("--------- Contenu de DG------------");
		for (Forme it : formes) {
			System.out.println(it);
		}
		System.out.println("---------------------------");
	}

	/**
	 * permettant d’initialiser un document avec la liste des cubes récupérés d’un
	 * fichier dont le nom est passé en paramètre
	 * 
	 * @param pFileName
	 * @throws IOException
	 */
	public void initDocWithCubesFromFile(String pFileName) throws IOException {

		// lire le fichier
		List<String> lignes = StringUtils.readFile(pFileName);

		// On efface le contenu du document graphique pour le réinitialiser avec la
		// liste des cubes qu'on va lire depuis un fichier
		formes.clear();

		int compteur = 0;
		for (int i = 0; i < lignes.size(); i++) {

			try {
				if (StringUtils.isCube(lignes.get(i))) {
					formes.add(StringUtils.extraireCubeV2(lignes.get(i + 1)));
				}

			} catch (MalformedException e) {

				// on compte le nombre de cube igonorés
				compteur++;
			}

		}

		if (compteur != 0) {
			System.out.println("Lors de l'initialisation il y a " + compteur + " cubes ignorés");

		}

	}

	/**
	 * Permet de calculer le poids total
	 * 
	 * @return
	 */
	public double poidsTotal() {

		double poid = 0.;
		for (Forme it : formes) {

			poid += it.calculerPoids();

		}

		return poid;

	}

}
