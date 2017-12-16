package com.web.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.security.Role;
import com.bo.security.Utilisateur;
import com.exceptions.DuplicateLoginException;
import com.services.UtilisateurService;
import com.web.BaseAction;

//Pour les actions de cette classe on les intercepte avec la pile d'intecepteur
//adminStack définit dans le fichier strust.xml
//cette pile permet d'authentifier l'utilisateur et de vérifier ses droits
//En effet l'utilisateur  ne peut pas executer les actions de cette classe car
//QcmAdminInterceptor va l'interdire d'accéder

@InterceptorRef(value = "adminStack")

// Résultat global
@Results({ @Result(name = "login", location = "/public/login.jsp"),

})

/**
 * Classe d'action implémentant les opérations d'administration des utilisateurs
 * 
 * @author T.BOUDAA
 *
 */

public class UserAction extends BaseAction {

	/** Le service métier */
	@Autowired
	private UtilisateurService userService;

	private List<Utilisateur> listUsers;

	private Utilisateur utilisateur;

	private List<Role> listRoles;

	private Long selectedRole;

	@Action(value = "initFormAddUser", results = {
			@Result(name = "success", location = "/private/admin/formAddUser.jsp") })
	public String initFormAddUser() {

		// Récupérer la liste des roles, car la vue aura besoin de cette liste
		// pour afficher le combo box
		listRoles = userService.getAllRoles();

		return SUCCESS;
	}

	@Action(value = "addUser", results = { @Result(name = "success", type = "redirectAction", location = "listUsers"),
			@Result(name = "error", location = "error.jsp"), })
	public String addUser() {

		try {

			// Appel de la coucher service métier pour ajouter un utilisateur
			utilisateur.setRole(userService.getRoleById(selectedRole));
			userService.addUtilisateur(utilisateur);

		} catch (DuplicateLoginException ex) {

			// En cas de problème , login existe déjà on enregiste une erreur
			// pour la vue
			addActionError("Opération non effectuée car login existe déjà");

			// à replacer par un logger, car ce n'est pas propre
			ex.printStackTrace();

			// afficher la page d'erreur (vue absente, donc à écrire)
			return "error";

		}

		// Sinon on enregiste un message de succès
		addActionMessage("Utilisateur ajouté avec succès");

		// rediriger vers l'action affichant la liste des utilisateurs
		return SUCCESS;
	}

	@Action(value = "listUsers", results = { @Result(name = "success", location = "/private/admin/listUsers.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String listUsers() {

		// En utilisant la couche service métier récupérer la liste des
		// utilisateur puis rediriger vers la vue pour les présenter
		listUsers = userService.getAllUsers();

		return SUCCESS;
	}

	public List<Utilisateur> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Utilisateur> listUsers) {
		this.listUsers = listUsers;
	}

	public UtilisateurService getUserService() {
		return userService;
	}

	public void setUserService(UtilisateurService userService) {
		this.userService = userService;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Role> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<Role> listRoles) {
		this.listRoles = listRoles;
	}

	public Long getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(Long selectedRole) {
		this.selectedRole = selectedRole;
	}

}
