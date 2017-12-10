package com.app.business.dao;

import java.util.List;

import com.app.business.bo.Lecteur;
import com.boudaa.dao.GenericDao;
import com.boudaa.dao.exceptions.EntityNotFoundException;

public interface LecteurDAO extends GenericDao<Lecteur, Long> {
	
	public List<Lecteur> getLecteurByName(String pName)  throws EntityNotFoundException;
	
	
	
}
