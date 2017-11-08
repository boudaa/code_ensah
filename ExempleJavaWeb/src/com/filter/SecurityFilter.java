package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(urlPatterns = { "/private/*" })
public class SecurityFilter implements Filter {

	private static final String CONNEXION_PAGE = "/connexion.jsp";

	public SecurityFilter() {
		System.out.println("SecurityFilter");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter");

		// On applique un Cast vers HttpServletRequest
		HttpServletRequest userRequest = (HttpServletRequest) request;
		// On récupère la session
		HttpSession session = userRequest.getSession();

		// Si l'utilisateur est connecté
		if (session.getAttribute("user") != null) {
			// passer la requête le long de la chaîne de filtres
			chain.doFilter(request, response);
		}

		else {
			// rediriger vers la page de connexion
			((HttpServletResponse) response).sendRedirect(request.getServletContext() + CONNEXION_PAGE);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
