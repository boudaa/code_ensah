package com.filter;

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

public class SecurityFilter implements Filter {

	private static Logger LOGGER;

	private static final String CONNEXION_PAGE = "/connexion.jsp";

	public SecurityFilter() {

		LOGGER = Logger.getLogger(getClass());

		LOGGER.info("Filter security est crée");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.info("security filter est appelé..");

		HttpServletRequest rq = (HttpServletRequest) request;

		// On récupère la session

		HttpSession session = rq.getSession();

		if (session.getAttribute("user") == null) {

			rq.getRequestDispatcher(CONNEXION_PAGE).forward(request, response);

		} else {
			chain.doFilter(request, response);

		}

		LOGGER.info("fin d'execution de security filter ..");

	}

}
