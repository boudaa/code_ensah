package com.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Etudiant;

/**
 * Cette classe servlet présente des exemples avec la session
 * 
 * @author BOUDAA
 *
 */

@WebServlet("/sessionExamples")
public class SessionExamplesServlet extends HttpServlet {

	public SessionExamplesServlet() {

	}

	/**
	 * Cette méthode stocke un objet dans la session
	 * 
	 * @param rq
	 *            : l'objet représentant la requete
	 * @param key
	 *            : une chaine de caractère qui represente la clé à utiliser
	 *            pour référencer l'objet dans la session
	 * @param object
	 *            : l'objet à stocker dans la session
	 */
	public void storeObjectInSession(HttpServletRequest rq, String key, Object object) {

		// On récupère la session

		HttpSession session = rq.getSession();

		// On stocke l'objet dans la session de l'utilisateur

		session.setAttribute(key, object);

	}

	/**
	 * Méthode qui permet de récupérer un objet de la session
	 * 
	 * @param rq
	 *            : l'objet représentant la requete
	 * @param key
	 *            : une chaine de caractère qui represente la clé à utiliser
	 *            pour récupérer l'objet dans la session
	 * @param object
	 *            : l'objet à récupérer de la session
	 */
	public Object getObjectFromSession(HttpServletRequest rq, String key) {

		// On récupère la session
		HttpSession session = rq.getSession();

		// On récupère l'objet de la session en se basant sur la clé
		return session.getAttribute(key);

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

		// Tache 1 : On stocke un objet étudiant dans la session
		if ("store".equals(param)) {
			Etudiant et = new Etudiant("Karimi", "Ali", 22);
			storeObjectInSession(request, "etudiant", et);

			// On insère du texte dans la réponse
			writeParagraph(response, "L'étudiant est bien ajoutée dans la session");

		}
		// Tache 2 : On récupère un objet étudiant de la session

		else if ("get".equals(param)) {

			// On récupère un étudiant de la session
			// L'objet récupéré de la session est généralement de type Object,
			// on s'assure qu'il s'agit exactement d'un objet de type Etudiant
			// par instanceof avant de forcer la conversion
			Etudiant etudiant = null;
			Object obj = getObjectFromSession(request, "etudiant");
			if (obj instanceof Etudiant) {
				etudiant = (Etudiant) obj;
			}

			// si un étudiant est trouvé
			if (etudiant != null) {
				// On insère du texte dans la réponse
				// on laisse la méthode toString de Etudiant prendera soin de
				// convertir l'objet en une belle chaine de caractère
				writeParagraph(response, "L'étudiant est trouvé : " + etudiant);
			}

			else {
				// On écrit un message indiquant que l'objet recherché n'existe
				// pas dans la session
				writeParagraph(response, "Aucun étudiant trouvé");

			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
