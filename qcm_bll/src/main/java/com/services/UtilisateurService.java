package com.services;

import java.util.List;

import com.bo.security.Role;
import com.bo.security.Utilisateur;
import com.exceptions.AuthentificationFailureException;
import com.exceptions.DuplicateLoginException;

public interface UtilisateurService {

	public List<Utilisateur> getAllUsers();

	public Utilisateur loadUserByUsername(String pLogin) throws AuthentificationFailureException;

	public void addUtilisateur(Utilisateur pUser) throws DuplicateLoginException;

	public void deleteUser(Utilisateur u);

	public List<Role> getAllRoles();

	public Role getRoleById(Long pRoleId);

}
