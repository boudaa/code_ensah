package com.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.Message;
import com.bo.Utilisateur;
import com.dal.UserDaoImpl;

public class InsertionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Utilisateur user = new Utilisateur(nom, prenom, login, password, 0, 0);

		ServletContext cntx = getServletContext();

		UserDaoImpl userDao = new UserDaoImpl();

		String errorPage = "/pages/error.jsp";
		String okPage = "/pages/operationOK.jsp";

		List<Message> messages = new ArrayList<Message>();

		try {

			// On teste si un utilisateur existe avec le login choisit

			if (userDao.getByColValue("login", login, "Utilisateur").size() != 0) {

				// Ajouter des message d'erreur dans la requete
				messages.add(new Message("Login existe déjà", Message.WARN));
				request.setAttribute("messages", messages);

				cntx.getRequestDispatcher(errorPage).forward(request, response);
				return;

			}

			userDao.create(user);

			// redirect to ok page
			messages.add(new Message("Utilisateur correctement ajouté", Message.INFO));
			request.setAttribute("messages", messages);
			cntx.getRequestDispatcher(okPage).forward(request, response);
			return;
		} catch (Exception e) {

			// redirect to error page
			messages.add(new Message("Une erreur est survenue", Message.ERROR));
			cntx.getRequestDispatcher(errorPage).forward(request, response);

			return;

		}

	}

}