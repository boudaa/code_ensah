package com.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Filtre permettant de vérifier si un utilisateur est déjé authentifié
 * 
 * @author T.BOUDAA
 *
 */
public class SecurityFilter implements Filter {

	/** la vue d'authentification */
	private static final String CONNEXION_PAGE = "/pages/loginForm.jsp";

	private final Logger LOGGER;

	public SecurityFilter() {
		LOGGER = Logger.getLogger(SecurityFilter.class);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.debug("Le filtre de sécurité commence son travail ici ... ");

		// On convertie l'objet de requete générique (indépendant des
		// protocoles) en un objet HTTP, toujours
		// faut faire éa pour le cas des filtres !
		HttpServletRequest rq = (HttpServletRequest) request;

		// On récupére la session
		HttpSession session = rq.getSession();

		// On vérifie si l'authentification a déjé eu lieu
		if (session.getAttribute("user") == null) {

			// Si non il faut interdir l'accés
			rq.getRequestDispatcher(CONNEXION_PAGE).forward(request, response);

			// Fin
			return;

		} else {

			// Si oui, alors continuer vers la resource suivante dans la chaine
			// (filtre suivant, servlet suivante ou jsp suivante..)
			chain.doFilter(request, response);

		}

	}

}
