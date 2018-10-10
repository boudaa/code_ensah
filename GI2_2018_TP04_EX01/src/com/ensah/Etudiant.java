package com.ensah;
import java.util.ArrayList;
import java.util.List;

public class Etudiant extends Personne {

	private String cne;

	private String filiere;

	private List<Pfe> listePfe = new ArrayList<Pfe>();

	public Etudiant(String pCne, String pFiliere, String pNom, String pPrenom,
			String pTele, String pEmail) {

		super(pNom, pPrenom, pTele, pEmail);
		cne = pFiliere;
		filiere = pCne;
	}

	/**
	 * Associe un PFE à son réalisateur
	 * 
	 * @param pPfe
	 */

	public void associatePfeToRealisateur(Pfe pPfe) {

		listePfe.add(pPfe);

	}

}
