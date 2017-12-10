package com.app.business.service;

import java.util.List;
import java.util.Map;

import com.app.business.bo.Book;
import com.app.business.bo.Rayon;
import com.app.business.bo.Theme;
import com.boudaa.dao.exceptions.EntityNotFoundException;

/**
 * 
 * Interface du Service métier permettant de gérer les livres
 * 
 * @author boudaa
 *
 */
public interface BookService {

	/** Enregistrer un livre */
	public void addNewBook(Book pBook);

	/** récupére la liste des livres */
	public List<Book> getAllBooks();

	/** récupére des données statistiques sur les livres */
	public Map<String, Number> getBooksStat();

	/** recherche un livre par son titre */
	public List<Book> finBookByTitle(String pTitle);

	/**
	 * recherche un livre par son identifiant
	 * 
	 * @throws EntityNotFoundException
	 */
	public Book finBookById(Long idBook) throws EntityNotFoundException;

	/** récupére la liste des rayons */
	public List<Rayon> getAllRayons();

	/** récupére la liste des thémes */
	public List<Theme> getAllThemes();

	/** recherche un théme par son nom */
	public Theme getThemeByNom(String pTheme);

	/** recherche un rayon par son nom */
	public Rayon getRayonByNom(String pRayon);

	/** récupére la liste des théme associés é un rayon */
	public List<String> getThemeOfRayon(String rayon);

	/** mise é jour des informations d'un livre*/
	public void updateBook(Book pBook) ;

	
	
	/** supprime un livre de la base de données 
	 * @throws EntityNotFoundException */
	public void deleteBook(Long pId) throws EntityNotFoundException;
}
