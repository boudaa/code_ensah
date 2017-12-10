package com.web;

import com.app.business.bo.Lecteur;
import com.app.business.service.LecteurService;

public class LecteurAction extends BaseAction {

	private Lecteur lecteur;

	private LecteurService lecteurService;

	public String addLecteur() {

		// TODO: Traitement applicatif à faire

		lecteurService.addLecteur(lecteur);

		// TODO: Traitement applicatif à faire

		return SUCCESS;
	}

	public Lecteur getLecteur() {
		return lecteur;
	}

	public void setLecteur(Lecteur lecteur) {
		this.lecteur = lecteur;
	}

	public LecteurService getLecteurService() {
		return lecteurService;
	}

	public void setLecteurService(LecteurService lecteurService) {
		this.lecteurService = lecteurService;
	}

}
