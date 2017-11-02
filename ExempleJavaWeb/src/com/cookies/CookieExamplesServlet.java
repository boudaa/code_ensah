package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Cette classe servlet présente des exemples avec les cookies
 * 
 * @author BOUDAA
 *
 */

@WebServlet("/cookiesExamples")
public class CookieExamplesServlet extends HttpServlet {

	public CookieExamplesServlet() {

	}

	/**
	 * Méthoed qui permet de tester l'ajout d'un cookie dans la reponse
	 * 
	 * @param res
	 *            : represente la réponse
	 * @param cookieName
	 *            : nom du cookie (key)
	 * @param cookieValue
	 *            : contenu du cookie (value)
	 */
	public void addCookieToHttpResponse(HttpServletResponse res, String cookieName, String cookieValue) {

		Cookie cookie = new Cookie(cookieName, cookieValue);

		res.addCookie(cookie);

	}

	/**
	 * Récupère tous les cookies envoyés dans la requete de l'utilisateur
	 * 
	 * @param rq
	 *            la requete
	 * @return tableau de coookies
	 */
	public Cookie[] getAllUserCookies(HttpServletRequest rq) {

		Cookie[] cookiesTab = rq.getCookies();

		return cookiesTab;

	}

	/**
	 * Permet d'écrire un paragraphe dans la réponse
	 * 
	 * @param response
	 *            : objet de réponse à envoyer à l'utilisateur
	 * @throws IOException
	 */
	public void writeParagraph(HttpServletResponse response, String p) throws IOException {

		PrintWriter out = response.getWriter();
		out.print("<p>" + p + "</p>");
	}

	/**
	 * Méthode qui s'execute lorsque la sevlet recoit une requete de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère un paramètre de la requete qui indique la tache demandée,
		// ce paramètre attendu est : task
		String param = request.getParameter("task");

		// Suite à ce paramère execute l'une des taches c-dessous :

		// Tache 1 : On stocke un cookie dans la réponse
		if ("store".equals(param)) {
			addCookieToHttpResponse(response, "cookie_test", "ceci_est_un_cookie_de_test");

			// On insère du texte dans la réponse pour afficher un message HTML
			// dans le navigateur
			writeParagraph(response, "un cookie est bien enregistré pour s'assurer regardez dans votre navigateur");

		}
		// Tache 2 : On récupère les cookies de l'utilisateur

		else if ("get".equals(param)) {

			Cookie[] cookies = getAllUserCookies(request);

			if (cookies.length == 0) {
				writeParagraph(response, "Aucun cookie envoyé");

			}

			else {

				// On affiche les cookies
				for (Cookie it : cookies) {
					writeParagraph(response, it.getName() + ":" + it.getValue());

				}

			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
