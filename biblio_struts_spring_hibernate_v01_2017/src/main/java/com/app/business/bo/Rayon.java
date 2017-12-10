package com.app.business.bo;

import java.util.List;

/**
 * Permet de d�finir un rayon dans la biblioth�que
 * 
 * @author BOUDAA
 *
 */
public class Rayon {

	private Long idRayon;

	private String nom;
	private List<Theme> themes;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public Long getIdRayon() {
		return idRayon;
	}

	public void setIdRayon(Long idRayon) {
		this.idRayon = idRayon;
	}

}
