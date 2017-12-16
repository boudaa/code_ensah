package com.web.interceptors;

import java.util.Map;


import com.bo.security.Utilisateur;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {

		// Accès à la session dans un intercepteur
		Map<String, Object> session = invocation.getInvocationContext().getSession();

		String result = null;
		/* pre-processing */

		// si déjà authentifié et ayant les autorisations
		Utilisateur user = (Utilisateur) session.get("user");

		if (user != null && user.getRole() != null && "ROLE_ADMIN".equals(user.getRole().getRoleName())) {

			/* Appeler l' action ou l' intercepteur suivant */
			return invocation.invoke();

		} else {

			return Action.LOGIN;
		}

	}

}