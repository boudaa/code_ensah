package com.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.GameState;


public class ReinitGameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		GameState gs = (GameState) session.getAttribute("gameState");

		if (gs != null) {

			gs.reinit();
		}

		// reinit old result avec une valeur impossible pour le dés
		session.setAttribute("old_result", -1);

		getServletContext().getRequestDispatcher("/back/userHome.jsp").forward(request, response);

		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
