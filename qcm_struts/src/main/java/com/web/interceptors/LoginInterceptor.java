package com.web.interceptors;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {

		// Accès à la session dans un intercepteur
		Map<String, Object> session = invocation.getInvocationContext().getSession();

		String result = null;
		/* pre-processing */

		// si déjà authentifié

		if (session != null && session.get("user") != null) {
			
			

			/* Appeler l' action ou l' intercepteur suivant */
			return invocation.invoke();

		} else {

			return Action.LOGIN;
		}

		
	}

}