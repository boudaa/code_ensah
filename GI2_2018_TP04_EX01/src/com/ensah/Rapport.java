package com.ensah;
public class Rapport implements Livrable {

	private String contenu;

	private int nbrPage;

	private String langue;

	public Rapport(String contenu, int nbrPage, String langue) {
		this.contenu = contenu;
		this.nbrPage = nbrPage;
		this.langue = langue;
	}

	public void print() {
		System.out.println(contenu);
	}

}
