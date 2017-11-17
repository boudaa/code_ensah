package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.GameState;
import com.bo.Message;
import com.bo.Utilisateur;
import com.simu.MemoryDb;

/**
 * Servlet implementation class GameServlet
 */
public class GameServlet extends HttpServlet {

	public GameServlet() {

	}

	protected void play(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// récupérer l'ancien résultat de la session

		HttpSession session = request.getSession();

		Utilisateur user = (Utilisateur) session.getAttribute("user");

		GameState gameSate = new GameState(user);

		if (session.getAttribute("gameState") != null) {
			gameSate = (GameState) session.getAttribute("gameState");
		}

		if (user.getCompteurLancer() == 8) {

			// Game over

			if (!gameSate.isGameOver()) {
				gameSate.addMessage(new Message("Game Oveer", 0));

				MemoryDb db = MemoryDb.getUniqueInstance();
				db.

			}
			gameSate.setGameOver(true);

			getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);
			return;

		} else {
			// Lancer un dès (générer un nombre aléatoire dans l'intervalle 1,6

			int resultat = new Random().nextInt(6) + 1;

			gameSate.addMessage(new Message(String.valueOf(resultat), 0));

			if (session.getAttribute("old_result") != null) {

				if (((Integer) session.getAttribute("old_result")).intValue() == resultat) {

					// Augmenter le score de 20 points
					user.setScore(user.getScore() + 20);

					gameSate.addMessage(new Message("Gagné !", 0));

				}

			}

			session.setAttribute("gameState", gameSate);

			session.setAttribute("old_result", resultat);

			user.incrementLance();

			getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);

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
