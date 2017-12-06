package com.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Utilisateur;
import com.dal.UserDaoImpl;

/**
 * Cette servlet gére le meilleieur score
 * 
 * @author T.BOUDAA
 *
 */
public class BestScoreServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On construit un DAO pour accéder aux données liées aux utilisateur
		UserDaoImpl userDao = new UserDaoImpl();

		// On récupére tous les utilisateurs de la base de données
		List<Utilisateur> users = userDao.getAll();

		// On stocke dans la requete (comme attribut) les utilisateurs. Cette
		// liste é une durée de vie = é durée de vie de la requete. Donc elle
		// n'existera plus é la fin du cycle de vie de la requete
		request.setAttribute("users", users);

		// On redirige vers la vue (redirection coté serveur)
		getServletContext().getRequestDispatcher("/back/bestScore.jsp").forward(request, response);

	}

}
