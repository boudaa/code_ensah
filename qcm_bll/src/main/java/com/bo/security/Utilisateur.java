package com.bo.security;

import java.util.Date;
import java.util.Set;

import com.bo.Qcm;

public class Utilisateur {

	private Long idUtilisateur;
	private String nom;
	private String prenom;
	private String login;

	private String email;

	private String password;

	private boolean enabled = true;
	private boolean accountNotExpired = true;
	private boolean accountNotLocked = true;
	private Date lastAccessDate;
	private Role role;
	private Set<Qcm> qcms;

	public Utilisateur() {
		role = new Role();
	}

	public Utilisateur(String nom, String prenom, String login, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public Utilisateur(String pLogin, String pPassword) {

		this.login = pLogin;
		this.password = pPassword;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNotExpired() {
		return accountNotExpired;
	}

	public void setAccountNotExpired(boolean accountNotExpired) {
		this.accountNotExpired = accountNotExpired;
	}

	public boolean isAccountNotLocked() {
		return accountNotLocked;
	}

	public void setAccountNotLocked(boolean accountNotLocked) {
		this.accountNotLocked = accountNotLocked;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Qcm> getQcms() {
		return qcms;
	}

	public void setQcms(Set<Qcm> qcms) {
		this.qcms = qcms;
	}
}
