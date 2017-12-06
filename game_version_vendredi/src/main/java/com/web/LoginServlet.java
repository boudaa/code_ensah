package com.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Message;
import com.bo.Utilisateur;
import com.dal.UserDaoImpl;

/**
 * Servlet d'authentification (simpliste) d'un utilisateur
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupére les données envoyées dans le formulaire
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		// On instancie la liste que nous utiliserons pour stocker les message
		// é passer é la vue
		List<Message> messages = new ArrayList<Message>();

		// Le DAO qui va nous permettre d'effectuer des opérations en base de
		// données pour tester si un utilisateur avec le login saisi existe en
		// base de données
		UserDaoImpl userDao = new UserDaoImpl();

		// On recherche l'utilisateur par login
		List<Utilisateur> users = userDao.getByColValue("login", login, "Utilisateur");

		// Si un utilisateur existe
		if (users.size() == 1) {

			// On vérifie que les mot de passe sont identiques
			if (users.get(0) != null && users.get(0).getPassword() != null
					&& users.get(0).getPassword().equals(password)) {

				// On stocke l'utilisateur authentifié dans la session
				request.getSession().setAttribute("user", users.get(0));

				// On envoit une vue qu'est la page home comme résultat
				getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);

				// Fin
				return;
			} else {
				messages.add(new Message("Login ou mot de passe incorrect", Message.WARN));

				request.setAttribute("messages", messages);

				// Sinon on affiche le formulaire d'authentification avec des
				// messages d'erreur
				getServletContext().getRequestDispatcher("/pages/loginForm.jsp").forward(request, response);

				return;
			}

		} else {
			messages.add(new Message("Login ou mot de passe incorrect", Message.WARN));

			request.setAttribute("messages", messages);

			// de meme si l'utilisateur est introuvable avec une recherche par
			// login
			getServletContext().getRequestDispatcher("/pages/loginForm.jsp").forward(request, response);

			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
