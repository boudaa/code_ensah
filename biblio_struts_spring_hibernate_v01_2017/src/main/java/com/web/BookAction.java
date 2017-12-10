package com.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.business.bo.Book;
import com.app.business.bo.Theme;
import com.app.business.service.BookService;
import com.boudaa.dao.exceptions.EntityNotFoundException;

/**
 * Une classe d'action Struts 2 pour traiter les requetes liées à la gestion des
 * livres. Cette classe fait appel à la couche Services métiers pour répondres
 * aux requetes
 * 
 * @author T. BOUDAA (tarikboudaa@yahoo.fr)
 * 
 *         Ce code est réalisé dans un cadre pédagogique.
 *
 */
public class BookAction extends BaseAction {

	/** Utilisé pour tracer les événements de cette action */
	protected final Logger TRACER = Logger.getLogger(getClass());

	/** Le livre traité dans les vues associées à cette action */
	private Book book;

	/** utilisée pour afficher la liste des livres dans les vues */
	private List<Book> livres ;

	/**
	 * Represente le titre du livre recherché dans la vue contenant un
	 * formulaire de recherche par titre
	 */
	private String searchedBook;

	/**
	 * Représente la liste des thèmes, cette liste est utilisée pour construire
	 * un combobox contenant les noms des thèmes dans les vues
	 */
	private List<String> listThemes;

	/**
	 * utiliser pour contenur le theme selectionné dans la vue (via combobox)
	 */
	// private String selectedTheme;

	/**
	 * Une reference au service métier responsable sur la gestion des livres.
	 * Spring injectera automatiquement une instance de bookServiceImpl dans
	 * cette varibale (Dependency Injection Design Pattern)
	 */
	private BookService bookService;

	public BookAction() {
		TRACER.debug("BookAction instanciée");
	}

	/**
	 * Cette méthode n'est pas une méthode d'action elle factorise un traitement
	 * répétitive
	 */
	private void initFormBook() {

		// on charge les données d'initialisation du formulaire
		// Ces données peuvent etre stockées dans la session de l'utilisateur si
		// c'est pas encore fait
		// (pour optimiser le nombre d'appels à la base de données

		if (getSession().getAttribute("listObjTheme") == null) {
			listThemes = new ArrayList<String>();
			// On construit la liste des thèmes
			List<Theme> listObjTheme = bookService.getAllThemes();
			for (Theme it : listObjTheme) {
				listThemes.add(it.getNom());
			}
			TRACER.debug("liste des thème est chargée de la base de données");

			getSession().setAttribute("listObjTheme", listObjTheme);
			getSession().setAttribute("listTheme", listThemes);

			TRACER.debug("liste des thème enregistrée dans la session");

		}

		else {
			TRACER.debug("la liste des thème est initialisée de la session");

			listThemes = (List<String>) getSession().getAttribute("listTheme");

		}
	}

	/**
	 * Méthode d'action utilisée pour initialiser le formulaire de mise à jour
	 * 
	 * 
	 * @return
	 */
	public String showFormUpdate() {

		TRACER.debug("la méthode showFormForUpdate est invoquée");

		// initialiser le formulaire
		initFormBook();

		// On récupére le paramètre id de la requete envoyée par le client

		String idbook = getRequest().getParameter("idbook");

		if (idbook != null && !idbook.isEmpty()) {

			try {
				book = bookService.finBookById(Long.valueOf(idbook));

				// selectedTheme = book.getTheme().getNom();

				// si un livre est trouvé on affiche un formulaire initialisé
				// avec les
				// informations du livre trouvé (cas de modification d'un livre)
				// et on affiche le formulaire de modification

			} catch (EntityNotFoundException e) {
				// le livre est introuvable on informe l'utilisateur et on trace
				// une erreur
				TRACER.error("Echec de chargement d'un livre avec l'id =" + idbook, e);

				// on affiche une page d'erreur
				addActionMessage(
						"Echec de l'opération : Le livre que vous avez sélectionné n'existe pas ou il a été supprimé");
				return "error";
			}

		} else {
			// le livre est introuvable on informe l'utilisateur et on trace
			// une erreur
			TRACER.error("Echec de chargement d'un livre avec l'id vide");

			// on affiche une page d'erreur
			addActionMessage("Echec de l'opération : Vous n'avez pas sélectionné un livre");
			return "error";
		}

		TRACER.debug("la méthode showFormForUpdate est executée avec succès");

		return "updateform";

	}

	/**
	 * Méthode d'action utilisée pour initialiser le formulaire d'ajout d'un
	 * livre
	 * 
	 * @return
	 */
	public String showFormSave() {

		TRACER.info("la méthode showFormSave est invoquée");

		// initialiser le formulaire
		initFormBook();

		TRACER.debug("la méthode showFormSave est executée avec succès");

		return "saveform";

	}

	public Theme getThemeFromUserSession(String pTheme) {
		// On récupére l'objet associé au theme choisit
		Theme theme = null;
		// d'abord on cosulte la session web
		if (getSession().getAttribute("listObjTheme") != null) {

			List<Theme> listObjTheme = (List<Theme>) getSession().getAttribute("listObjTheme");
			for (Theme it : listObjTheme) {
				System.out.println("theme list=" + it.getNom());
				if (pTheme != null && pTheme.equals(it.getNom())) {
					theme = it;
					TRACER.debug("thème récupéré de la session" + theme);
					break;

				}
			}

		}

		return theme;
	}

	/**
	 * méthode d'action qui permet d'enregistrer un livre
	 * 
	 * @return
	 */
	public String processFormData() {

		TRACER.info("la méthode processFormData est invoquée");

		// On récupére l'objet associé au theme choisit

		Theme theme = getThemeFromUserSession(book.getTheme().getNom());

		// si le thème est introuvable dans la session, on consulte la base de
		// données via le service metier
		if (theme == null) {
			theme = bookService.getThemeByNom(book.getTheme().getNom());
		}

		// Si introuvebale dans la base de données, on fait rien
		// l'erreur sera traité comme exception globale
		// car la méthode getThemeByNom déclenche une exception si le livre est
		// introuvable

		// On associe le theme avec le livre à enregistrer
		book.setTheme(theme);

		// on enregistre l'entité book contenant les données envoyées via le
		// formulaire
		bookService.addNewBook(book);

		TRACER.debug("la méthode processFormData est executée");

		return SUCCESS;

	}

	/**
	 * Méthode d'action qui permet de modifier un livre
	 * 
	 * @return
	 */
	public String updateBook() {
		TRACER.error("début de la méthode updateBook");

		// On récupére l'objet associé au theme choisit

		// d'abord on consulte la session web
		Theme theme = getThemeFromUserSession(book.getTheme().getNom());
		// d'abord on consulte la session web
		// si le thème est introuvable dans la session, on consulte la base de
		// données via le service metier
		if (theme == null) {
			TRACER.debug("Thème récupéré de la base de données:" + theme);

			theme = bookService.getThemeByNom(book.getTheme().getNom());
		}

		try {

			// On associe le theme avec le livre à enregistrer
			book.setTheme(theme);

			bookService.updateBook(book);

			// on affiche un message dans la vue
			addActionMessage("Opération effectuée avec succés ");

		} catch (Exception ex) {
			// on affiche un message dans la vue
			addActionError("Erreur : Opération non effectuée");

			// On trace pour des raison de débougage
			TRACER.error("la mise à jour du livre a rencontrée une erreur à caude de : ", ex);
		}

		initFormBook();

		return SUCCESS;

	}

	/**
	 * méthode d'action qui permet de supprimer un livre
	 * 
	 * @return
	 */
	public String deleteBook() {

		String idbook = getRequest().getParameter("idbook");

		try {
			bookService.deleteBook(Long.valueOf(idbook));

			// on affiche un message dans la vue
			addActionMessage("Livre supprimé avec succés");

		} catch (EntityNotFoundException ex) {

			// ce message doit etre affiché par la vue
			addActionError("Erreur : Le livre à supprimer n'existe pas");
			// On trace pour des raison de débougage
			TRACER.debug("essaie de suppression d'un livre avec id non existant =" + idbook + " : ", ex);
		}

		return SUCCESS;

	}

	/**
	 * méthode d'action qui permet d'afficher la liste des livres
	 * 
	 * @return
	 */
	public String list() {

		livres = bookService.getAllBooks();

		return SUCCESS;

	}

	/**
	 * méthode d'action qui permet de rechercher un livre par son titre
	 */
	public String searchBook() {
		
		TRACER.error("début de la méthode searchBook avec searchedBook=" + searchedBook);

		livres = bookService.finBookByTitle(searchedBook);

		return SUCCESS;

	}

	// Getters/setters

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getLivres() {
		return livres;
	}

	public void setLivres(List<Book> livres) {
		this.livres = livres;
	}

	public String getSearchedBook() {
		return searchedBook;
	}

	public void setSearchedBook(String searchedBook) {
		this.searchedBook = searchedBook;
	}

	public List<String> getListThemes() {
		return listThemes;
	}

	public void setListThemes(List<String> listThemes) {
		this.listThemes = listThemes;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}



}
