package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class LogginFilter
 */
public class LogginFilter implements Filter {

	/** Permet de faire la journalisation */
	private static Logger LOGGER;

	public LogginFilter() {

		// On initialise le logger
		LOGGER = Logger.getLogger(getClass());

		LOGGER.info("LogginFilter est crée...");

	}

	public void init(FilterConfig fConfig) throws ServletException {

		LOGGER.info("init est executée ...");

	}

	public void destroy() {

		LOGGER.info("destroy est executée ...");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Preprocessing

		LOGGER.info("Preprocessing ...");

		// On récupère les objet de requet et on applique un cast
		// vers le type http
		HttpServletRequest rq = (HttpServletRequest) request;

		// On récupère l'adresse IP du client (ou du dernier proxy)
		String ip = rq.getRemoteAddr();

		String requestedURI = rq.getRequestURI();

		// On ajoute dans le journal l'adresse IP du client et l'URI de la
		// ressource demandée sur le serveur
		LOGGER.info("IP=" + ip + " Requested URI=" + requestedURI + " méthode =" + rq.getMethod());

		// passer la main au filtre suivant dans la chaine
		chain.doFilter(request, response);

		// Postprocessing

		LOGGER.info("Postporcessing...");

	}

}
