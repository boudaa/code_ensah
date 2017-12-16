package com.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.security.Utilisateur;
import com.exceptions.AuthentificationFailureException;
import com.opensymphony.xwork2.ActionContext;
import com.services.UtilisateurService;
import com.web.BaseAction;

/**
 * Action permettant de faire l'authentification d'un utilisateur
 * 
 * @author Boudaa
 *
 */
@Results({ @Result(name = "login", location = "/public/login.jsp") })
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

	@Action(value = "loginAction", results = { @Result(name = "success", location = "/public/login.jsp"),
			@Result(name = "success", type = "redirectAction", location = "initUserHome") })
	public String login() {

		// Si les champs sont vides (normalement il faut écrire un validateur
		// XML)
		if (userName.isEmpty() || password.isEmpty()) {
			addActionError("Username/Password ne peuvent pas etres vides");
			return LOGIN;
		}

		try {

			// Appel de la couche metier pour vérifier si un utilisateur existe
			// avec ce login
			Utilisateur user = (Utilisateur) utilisateurService.loadUserByUsername(userName);

			// On compare les mots de passe
			if (user != null && password.equals(user.getPassword())) {

				// On met l'utilisateur dans la session
				session.put("user", user);

				// On redirige vers l'action qui initialise le home de
				// l'utilisateur
				return SUCCESS;
			} else {

				// Sinon enregistrer des erreurs pour les affichers dans la vue
				addActionError("Username ou Password incorrectes");
				return LOGIN;
			}

		} catch (AuthentificationFailureException e) {

			// En cs d'echec de l'authenitification (username introuvable) en
			// enregistre des erreurs
			addActionError("Username ou Password incorrectes");

			// A supprimer (c'est juste pour débougage) , il fallait utiliser un
			// logger
			e.printStackTrace();

			// retourner le résultat globale Login
			return LOGIN;

		}

	}

	/** redirige l'utilisateur à son home */
	@Action(value = "initUserHome", results = { @Result(name = "successAdmin", location = "/private/admin/home.jsp"),
			@Result(name = "successUser", location = "/private/user/home.jsp") })
	public String initUserHome() {

		// On obtient l'utilisateur de la session
		Utilisateur user = (Utilisateur) session.get("user");
		// Si Admin
		if (user != null && user.getRole() != null && "ROLE_ADMIN".equals(user.getRole().getRoleName())) {
			return "successAdmin";

		}
		// Si user
		else if (user != null && user.getRole() != null && "ROLE_USER".equals(user.getRole().getRoleName())) {

			return "successUser";

		}

		// Si role inconnu
		return LOGIN;

	}

	@Action(value = "logout")
	public String logout() {

		// Code permettant d'invalider la session (voir struts 2 docummentation)
		((org.apache.struts2.dispatcher.SessionMap) ActionContext.getContext().getSession()).invalidate();

		// Page de login (résultat global définit en tete de la classe)
		return LOGIN;
	}

	// Struts utilsiera cette méthode pour injecter la session
	public void setSession(Map<String, Object> pSession) {
		session = pSession;
	}

	///////////////////////////////////////
	// Getters and sessters
	///////////////////////////////////////
	public String getUserName() {
	
		return userName;
	}

	public void setUserName(String userName) {
		
		System.out.println("Appel de setUserName  avec "+ userName);
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		System.out.println("Appel de setPassword  avec "+ password);

		this.password = password;
	}

}
