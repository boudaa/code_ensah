package com.ensah;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cette classe permet de gérer les message
 * 
 * @author boudaa
 *
 */
public class MessageManger {

	/** permet de stocker les messages */
	private List<Message> messages = new LinkedList<Message>();

	/**
	 * cette méthode permet de supprimé un messsage avec son identifiant
	 * 
	 * @param id
	 *            identifiant du message
	 * @return retourne l'état de succès ou non de cette méthode
	 */
	public boolean deleteMessage(int id) {

		boolean found = false;
		int i = 0;
		// rechercher le message avec l'identifiant passé en paramètre
		for (i = 0; i < messages.size(); i++) {

			if (messages.get(i).getId() == id) {
				found = true;
				break;
			}

		}

		if (found) {
			messages.remove(i);

		}

		return found;

	}

	/**
	 * Retourne le premier élément dans la liste
	 * 
	 * @return
	 */
	public Message getFirstMessage() {

		return messages.get(0);
	}

	/**
	 * Retourne le dernier élément dans la liste
	 * 
	 * @return
	 */
	public Message getLastMessage() {

		return messages.get(messages.size() - 1);
	}

	/**
	 * Rechercher un élément par son identifiant
	 * 
	 * @param id
	 * @return
	 */
	public Message findMessageById(int id) {

		for (Message it : messages) {

			if (it.getId() == id)
				return it;
		}

		return null;
	}

	/**
	 * Mettre à jour un message par les données passées dans l'objet en paramètre
	 * 
	 * @param m
	 * @return
	 */
	public boolean updateMessage(Message m) {

		Message msg = findMessageById(m.getId());

		if (msg != null) {
			msg.setContenu(m.getContenu());
			msg.setDate(m.getDate());
			msg.setTitre(m.getTitre());

			return true;

		}

		return false;

	}

	/**
	 * Recherche un message par son titre
	 * 
	 * @param pTitre
	 * @return
	 */
	public List<Message> findMessageByTitle(String pTitre) {
		List<Message> searchResult = new ArrayList<Message>();
		for (Message it : messages) {
			if (it.getTitre().equals(pTitre))
				searchResult.add(it);
		}

		return searchResult;
	}

	/**
	 * Affiche tous les messages de la liste
	 */
	public void display() {

		System.out.println("-----Contenu de la liste-----");
		for (Message it : messages) {
			System.out.println(it.toString());
		}

	}

	public void displaySearchResult(List<Message> results) {
		System.out.println("Résultat de recherche :");
		if (results.size() == 0) {
			System.out.println("Il n y a aucun élément qui correspond à votre recherche");
		} else {
			for (Message it : results) {
				System.out.println(it);
			}
		}
	}

	/**
	 * Ajoute un message dans la liste des messages
	 * 
	 * @param m
	 */
	public void dddMessage(Message m) {

		messages.add(m);
	}

	/**
	 * Retourne tous les messages
	 * 
	 * @return
	 */
	public List<Message> getAllMessages() {

		return messages;

	}

}
