package com.dal;

import com.bo.Utilisateur;
import com.genericdao.GenericDAOImpl;

/**
 * Un DAO qui gére les opérations CRUD sur les Utilisateurs
 * 
 * Son implémentation se base sur une implémentation générique GenericDAOImpl
 * par héritage et par positionnement du paramétre T sur Utilisateur
 * 
 * La classe passe la classe manipulée qu'est de type Utilisateur.class au
 * constructeur de la classe de base GenericDAOImpl. En effet, l'objet de type
 * Class est necessaire dans quelques méthodes de GenericDAOImpl (dans findById
 * par exemple)
 * 
 * @author T.BOUDAA
 *
 */
public class UserDaoImpl extends GenericDAOImpl<Utilisateur> {

	public UserDaoImpl() {
		super(Utilisateur.class);
	}

}
