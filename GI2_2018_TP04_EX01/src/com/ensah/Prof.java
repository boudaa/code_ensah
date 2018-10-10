package com.ensah;
import java.util.ArrayList;
import java.util.List;

public class Prof extends Personne {

	private String grade;

	private String cin;

	private List<Pfe> listePfe;

	public Prof(String pGrade, String pCin, String pNom, String pPrenom,
			String pTele, String pEmail) {

		super(pNom, pPrenom, pTele, pEmail);
		grade = pGrade;
		cin = pCin;

		// On initialise la liste vide
		listePfe = new ArrayList<Pfe>();
	}

	/**
	 * Permet d'associer un PFE à son encadrant
	 * 
	 * @param pPfe
	 */
	public void associatePfeToEncadrant(Pfe pPfe) {

		listePfe.add(pPfe);

	}
}
