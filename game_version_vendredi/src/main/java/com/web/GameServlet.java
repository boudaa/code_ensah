package com.web;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.GameState;
import com.bo.Message;
import com.bo.Utilisateur;
import com.dal.UserDaoImpl;

/**
 * Implémente les régles du jeu
 */
public class GameServlet extends HttpServlet {

	public GameServlet() {

	}

	/**
	 * Méthode permettant é un utilisateur de jouer
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// On récupére la session de l'utilisateur en cours
		HttpSession session = request.getSession();

		// On récupére de la session, les informations du joueur en cours
		Utilisateur user = (Utilisateur) session.getAttribute("user");

		// On initialiser l'objet qui permet de suivre l'état d'une partie de
		// jeu de l'utilisateur en cours
		GameState gameSate = new GameState(user);

		// Un dao pour la gestion d'accés aux utilisateurs en base de données
		UserDaoImpl userDao = new UserDaoImpl();

		// Si un objet gameState n'existe pas dans la session, donc c'est le
		// moment pour stocker cet objet dans la session
		if (session.getAttribute("gameState") != null) {
			gameSate = (GameState) session.getAttribute("gameState");
		}

		// On teste si le nombre de lancés autorisés par le jeu n'est pas
		// atteint. Si oui "Game over"
		if (user.getCompteurLancer() == 20) {

			if (!gameSate.isGameOver()) {
				// On ajoute un message d'information
				gameSate.addMessage(new Message("Game Oveer", Message.INFO));

				// On vérifie s'il faut mettre é jour le meilleur score du jour
				// en base de données
				if (user.getScore() > user.getBestScore()) {

					// Si oui c'est le DAO qui s'en occupe
					user.setBestScore(user.getScore());
					userDao.update(user);
				}

			}

			// On indique que le jeu est terminé
			gameSate.setGameOver(true);

			// On redirige vers la vue par une redirection au niveau du serveur
			getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);

			// On arrete l'execution
			return;

		} else { // on peut ométtre else ici car on fait un retrun é la fin de
					// if

			// Lancer un dés (générer un nombre aléatoire dans l'intervalle 1,6)
			int resultat = new Random().nextInt(6) + 1;

			// On ajoute un message indiquant le résultat du lancé
			gameSate.addMessage(new Message(String.valueOf(resultat), Message.INFO));

			// On compare le résultat en cours avec l'ancien résultat, ce
			// dernier existe dans la session si ce n'est pas le premier lancé
			if (session.getAttribute("old_result") != null) {

				// si les deux résultats sont les memes on incrémente le score
				// avec 20 points
				if (((Integer) session.getAttribute("old_result")).intValue() == resultat) {

					// Augmenter le score de 20 points
					user.setScore(user.getScore() + 20);

					// On ajoute une indication du succés dans un message
					// d'information
					gameSate.addMessage(new Message("Gagné !", Message.INFO));

				}

			}

			// enregistrer dans la session, l'état en cours du jeu
			session.setAttribute("gameState", gameSate);
			// et le résultat obtenu dans le dernier lancé
			session.setAttribute("old_result", resultat);
			// on incrémente le nombre de lancés
			user.incrementLance();
			// on redirige vers la vue qui va afficher les message associés au
			// dernier
			// lancé
			getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);

			// Fin
			return;

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		play(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		play(request, response);

	}

}
