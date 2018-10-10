package com.ensah;

/**
 * Classe qui represente les messages
 * 
 * @author boudaa
 *
 */
public class Message {

	private int id;

	private String titre;

	private String date;

	private String contenu;

	public Message() {

	}

	public String toString() {

		return "message id = " + id + " titre : " + titre;
	}

	public Message(int id, String titre, String date, String contenu) {

		this.id = id;
		this.titre = titre;
		this.date = date;
		this.contenu = contenu;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getId() {
		return id;
	}

	public void setId(int pId) {
		id = pId;
	}

}
