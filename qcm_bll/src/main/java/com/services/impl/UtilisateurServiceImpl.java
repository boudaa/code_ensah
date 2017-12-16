package com.services.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.security.Role;
import com.bo.security.Utilisateur;
import com.dao.RoleDao;
import com.dao.UtilisateurDao;
import com.exceptions.AuthentificationFailureException;
import com.exceptions.DuplicateLoginException;
import com.services.UtilisateurService;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao userDao;

	@Autowired
	private RoleDao roleDao;

	protected final Log log = LogFactory.getLog(getClass());

	public Utilisateur loadUserByUsername(String pLogin) throws AuthentificationFailureException {
		Utilisateur lUser = null;

		// On récupère un objet de domaine de type User ayant comme login pLogin
		try {
			lUser = userDao.getUserByLogin(pLogin);

		} catch (ObjectRetrievalFailureException ex) {
			ex.printStackTrace();

			// nous relançons une AuthentificationFailureException si aucun
			// utilisateur
			// ne correspond à cet login
			log.debug("Erreur d'authentification avec le login : " + pLogin);
			throw new AuthentificationFailureException("User " + pLogin + " not exists", ex);

		}

		// Si un utilisateur correspond à cet identifiant, nous en profitons
		// pour mettre à jour sa date de dernière connexion
		lUser.setLastAccessDate(Calendar.getInstance().getTime());
		userDao.update(lUser);

		return lUser;
	}

	public void addUtilisateur(Utilisateur user) throws DuplicateLoginException {

		// Dans la réalité il faut effectuer un hashage des mot de passer
		// pour hacher avec SHA1
		// ShaPasswordEncoder encoder = new ShaPasswordEncoder();

		// Hachage du mot de passe avec un gain de sel variable = login
		// String cryptedPassword = encoder.encodePassword(user.getPassword(),
		// user.getLogin());

		// affecter le mot de passe haché
		// user.setPassword(cryptedPassword);

		// stockage de l'utilisateur dans la base de données
		try {
			userDao.create(user);

		} catch (DataIntegrityViolationException ex) {
			log.error("erreur d'ajout d'un utilisateur à cause de l'exception " + ex
					+ " . un utilisateur avec le login " + user.getLogin() + " existe déjà dans la base de données");
			throw new DuplicateLoginException("Erreur d'inscription, login existe déjà", ex);
		}

	}

	public List<Utilisateur> getAllUsers() {
		return userDao.getAll();
	}

	public void deleteUser(Utilisateur u) {

		userDao.delete(u.getIdUtilisateur());

	}

	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}

	public Role getRoleById(Long pRoleId) {
		return roleDao.findById(pRoleId);
	}

}
