package com.app.business.dao.impl;

import com.app.business.bo.Theme;
import com.app.business.dao.ThemeDao;
import com.boudaa.dao.impl.GenericDaoImpl;

/**
 * Implémentation du DAO ThemeDao. Cette classe hérite toutes les
 * fonctionnalités du DAO Generic en indiquant les valeurs de T et PK
 * 
 * @author boudaa
 *
 */
public class ThemeDaoImpl extends GenericDaoImpl<Theme, Long> implements
		ThemeDao {

	public ThemeDaoImpl() {

		// car on travail sur des objets de la classe Theme, il y a des méthodes
		// hibernate qui auront besoin de cette information cf. le code de
		// generic dao
		super(Theme.class);
	}

}
