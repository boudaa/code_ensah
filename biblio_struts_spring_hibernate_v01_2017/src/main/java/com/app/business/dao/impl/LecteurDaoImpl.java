package com.app.business.dao.impl;

import java.util.List;

import com.app.business.bo.Lecteur;
import com.app.business.dao.LecteurDAO;
import com.boudaa.dao.exceptions.EntityNotFoundException;
import com.boudaa.dao.impl.GenericDaoImpl;

public class LecteurDaoImpl extends GenericDaoImpl<Lecteur, Long> implements
		LecteurDAO {

	public LecteurDaoImpl() {

		super(Lecteur.class);

	}

	public List<Lecteur> getLecteurByName(String pName)
			throws EntityNotFoundException {

		// soit avec : une requete HQL et la méthode find
		// List<Lecteur> list = (List<Lecteur>) getHibernateTemplate().find(
		// "from Lecteur where nom = ? ", pName);

		// ou

		List<Lecteur> list = getEntityByColumn("Lecteur", "nom", pName);

		if (list == null || list.isEmpty()) {

			throw new EntityNotFoundException("Objet recherché introuvable");
		}

		return list;

	}

}
