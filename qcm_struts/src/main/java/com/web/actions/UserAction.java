package com.web.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.security.Role;
import com.bo.security.Utilisateur;
import com.exceptions.DuplicateLoginException;
import com.services.UtilisateurService;
import com.web.BaseAction;

/**
 * Classe d'action implémentant les opérations d'administration des utilisateurs
 * 
 * @author T.BOUDAA
 *
 */
public class UserAction extends BaseAction {

	@Autowired
	private UtilisateurService userService;

	private List<Utilisateur> listUsers;

	private Utilisateur utilisateur;

	private List<Role> listRoles;

	private Long selectedRole;

	@Action(value = "initFormAddUser", results = { @Result(name = "success", location = "/public/formAddUser.jsp") })
	public String initFormAddUser() {

		// Récupérer la liste des roles
		listRoles = userService.getAllRoles();

		return SUCCESS;
	}

	@Action(value = "showLoginPage", results = { @Result(name = "success", location = "/public/login.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String addUser() {

		try {
			utilisateur.setRole(userService.getRoleById(selectedRole));
			userService.addUtilisateur(utilisateur);

		} catch (DuplicateLoginException ex) {
			addActionError("Opération non effectuée à cause d'une erreur");

			ex.printStackTrace();
			return "error";

		}
		addActionMessage("Utilisateur ajouté avec succès");
		return SUCCESS;
	}

	
	@Action(value = "listUsers", results = { @Result(name = "success", location = "/private/admin/listUsers.jsp"),
			@Result(name = "error", location = "error.jsp"), })
	public String listUsers() {
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
