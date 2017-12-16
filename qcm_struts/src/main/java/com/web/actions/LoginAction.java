package com.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.security.Utilisateur;
import com.exceptions.AuthentificationFailureException;
import com.services.UtilisateurService;
import com.web.BaseAction;

/**
 * Action permettant de faire l'authentification d'un utilisateur
 * 
 * @author Boudaa
 *
 */

public class LoginAction extends BaseAction implements SessionAware {

	// Struts injectera la session dans cetattribut
	private Map<String, Object> session;

	// Spring injectera le service
	@Autowired
	private UtilisateurService utilisateurService;

	// Struts va recevoir de la vue automatiquement la valeur de cet attribut
	// (appel de setUserName automatiquement )
	private String userName;

	// Recoit la valeur du champs password ds la vue
	private String password;

	/** affiche logig form */

	@Action(value = "showLoginPage", results = { @Result(name = "success", location = "/public/login.jsp") })
	public String showLoginPage() {

		return SUCCESS;
	}

	@Action(value = "login", results = { @Result(name = "success", location = "/public/login.jsp"),
			@Result(name = "success", type = "redirectAction", location = "initUserHome") })
	public String login() {
		if (userName.isEmpty() || password.isEmpty()) {
			addActionError("Username/Password ne peuvent pas etres vides");
			return LOGIN;
		}

		try {
			Utilisateur user = (Utilisateur) utilisateurService.loadUserByUsername(userName);

			if (user != null && password.equals(user.getPassword())) {
				session.put("user", user);
				return SUCCESS;
			} else {
				addActionError("Username ou Password incorrectes");
				return LOGIN;
			}

		} catch (AuthentificationFailureException e) {

			e.printStackTrace();
			return LOGIN;

		}

	}

	/** redirige l'utilisateur à son home */
	@Action(value = "initUserHome", results = {
			@Result(name = "successAdmin", type = "redirectAction", location = "/private/admin/home.jsp"),
			@Result(name = "successUser", location = "/private/user/home.jsp") })
	public String initUserHome() {

		Utilisateur user = (Utilisateur) session.get("user");

		// Si Admin
		if ("ROLE_ADMIN".equals(user.getRole().getRoleName())) {
			return "successAdmin";

		}
		// Si user
		else if ("ROLE_USER".equals(user.getRole().getRoleName())) {

			return "successUser";

		}

		return SUCCESS;

	}

	// pour injecter la session
	public void setSession(Map<String, Object> pSession) {
		session = pSession;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
