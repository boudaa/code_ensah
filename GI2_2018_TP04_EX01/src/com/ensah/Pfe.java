package com.ensah;
import java.util.Date;
import java.util.List;

public class Pfe {

	private String sujet;
	private Date dateDebut;
	private String nomEntreprise;
	/**
	 * Livrable du PFE
	 */
	private List<Livrable> livrables;

	/** Le jury */
	private Jury jury;

	/** Le réalisateur */
	private Etudiant realisateur;

	/** L'encadrant */
	private Prof encadrant;

	private double moyenne;

	public Pfe(String sujet, Date dateDebut, String nomEntreprise,
			List<Livrable> livrables, double moyenne, Prof p, Etudiant r) {
		this.sujet = sujet;
		this.dateDebut = dateDebut;
		this.nomEntreprise = nomEntreprise;
		this.livrables = livrables;
		this.moyenne = moyenne;
		this.realisateur = r;
		this.encadrant = p;
	}

	public void affecterJury(Jury pJury) {

		jury = pJury;

	}

	public void afficheLivrable() {

		for (Livrable it : livrables) {
			it.print();
		}
	}

}
