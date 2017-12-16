package com.bo;

import java.util.Set;

import com.bo.security.Utilisateur;

public class Joueur extends Utilisateur {

	private Set<Qcm> qcms;

	public Set<Qcm> getQcms() {
		return qcms;
	}

	public void setQcms(Set<Qcm> qcms) {
		this.qcms = qcms;
	}

}
