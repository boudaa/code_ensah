package com.exemple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;

/**
 * Classe d'implémentation d'un DAO pour Etudiant
 * 
 * @author Tarik BOUDAA
 * 
 */
public class EtudiantDao {

	private final Logger LOG = Logger.getLogger(getClass());

	private Connection connect;

	public EtudiantDao() throws DataBaseException {

		connect = DBConnection.getConnexion();
	}

	public Long add(Etudiant pEtudiant) throws DataBaseException {

		Long idPeronne = Long.valueOf(-1);

		// la requete paramétrée
		String lSavePersonne = "INSERT INTO ETUDIANT "
				+ "   (NOM , PRENOM, CIN, DATENAISSANCE, ADRESSE) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement saveNewEtudiantStatement;

		try {

			saveNewEtudiantStatement = connect.prepareStatement(lSavePersonne, Statement.RETURN_GENERATED_KEYS);

			// On définit les paramètre de la requete paramétrée
			saveNewEtudiantStatement.setString(1, pEtudiant.getNom());
			saveNewEtudiantStatement.setString(2, pEtudiant.getPrenom());
			saveNewEtudiantStatement.setString(3, pEtudiant.getCni());
			saveNewEtudiantStatement.setDate(4, new Date(pEtudiant.getDateNaissance().getTime()));
			saveNewEtudiantStatement.setString(5, pEtudiant.getAdresse());

			// On execute la requete
			int rowCount = saveNewEtudiantStatement.executeUpdate();

			// On récupère la clé primaire auto-generée
			ResultSet results = saveNewEtudiantStatement.getGeneratedKeys();
			if (results.next()) {
				idPeronne = Long.valueOf(results.getLong(1));
			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDbOperationError("erreur lors de la sauvegarde de données ", ex);

		}
		return idPeronne;

	}

	public void update(Etudiant pEtudiant) throws DataBaseException {

		// requete SQL de mise à jour
		String strUpdateEtudiant = "UPDATE ETUDIANT SET NOM = ?, PRENOM = ?, CIN = ?, DATENAISSANCE = ?, ADRESSE = ? WHERE ID=?";

		try {

			PreparedStatement stmtUpdateEtudiant = connect.prepareStatement(strUpdateEtudiant);

			stmtUpdateEtudiant.clearParameters();

			stmtUpdateEtudiant.setString(1, pEtudiant.getNom());
			stmtUpdateEtudiant.setString(2, pEtudiant.getPrenom());
			stmtUpdateEtudiant.setString(3, pEtudiant.getCni());
			stmtUpdateEtudiant.setDate(4, new Date(pEtudiant.getDateNaissance().getTime()));
			stmtUpdateEtudiant.setString(5, pEtudiant.getAdresse());
			stmtUpdateEtudiant.setLong(6, pEtudiant.getId());

			stmtUpdateEtudiant.executeUpdate();

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDbOperationError("erreur lors de la lise à jour de données ", ex);
		}

	}

	public Etudiant find(Long pId) throws DataBaseException, ObjectNotFoundException {

		// requete paramétrée permet d'avoir un etudiant par son id
		String lGetEtudiant = "SELECT * FROM ETUDIANT WHERE id = ?";

		Etudiant etudiant = null;

		try {

			PreparedStatement stmtGetEtudiant = connect.prepareStatement(lGetEtudiant);
			stmtGetEtudiant.clearParameters();
			stmtGetEtudiant.setLong(1, Long.valueOf(pId));

			ResultSet result = stmtGetEtudiant.executeQuery();

			// Si trouvé alors conversion du resultset on un objet metier
			if (result.next()) {

				// on récupère les résultat de la requete

				String lNom = result.getString("NOM");
				String lPrenom = result.getString("PRENOM");
				String lCIn = result.getString("CIN");
				Date lDateNaissance = result.getDate("DATENAISSANCE");
				String lAdrssse = result.getString("ADRESSE");

				// On construit un objet de type etudiant

				etudiant = new Etudiant(lCIn, lNom, lPrenom, lAdrssse, lDateNaissance);
			} else {
				throw new ObjectNotFoundException("Object with id=" + pId + " is not found in database");
			}
		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDbOperationError("erreur lors de la récupération de données avec find ", ex);
		}

		return etudiant;
	}

	public void delete(Long pId) throws DataBaseException {

		// requete pour la suppression d'un etudiant
		String strDeleteEtudiant = "DELETE FROM ETUDIANT WHERE ID = ?";

		try {

			PreparedStatement stmtDeleteEtudiant = connect.prepareStatement(strDeleteEtudiant);

			stmtDeleteEtudiant.clearParameters();
			stmtDeleteEtudiant.setLong(1, Long.valueOf(pId));
			stmtDeleteEtudiant.executeUpdate();

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDbOperationError("erreur lors de la suppresion de données ", ex);
		}

	}

	public List<Etudiant> getAll() throws DataBaseException {
		// requete paramétrée permet d'avoir un compte par son numéro
		String lGetCompte = "SELECT * FROM ETUDIANT";

		List<Etudiant> list = new ArrayList<Etudiant>();

		try {

			PreparedStatement stmtGetCompte = connect.prepareStatement(lGetCompte);

			ResultSet result = stmtGetCompte.executeQuery();
			while (result.next()) {

				String lNom = result.getString("NOM");
				String lPrenom = result.getString("PRENOM");
				String lCIn = result.getString("CIN");
				Date lDateNaissance = result.getDate("DATENAISSANCE");
				String lAdrssse = result.getString("ADRESSE");

				list.add(new Etudiant(lCIn, lNom, lPrenom, lAdrssse, lDateNaissance));

			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDbOperationError("erreur lors de la récupération de données ", ex);

		}

		return list;
	}

	public void handleDbOperationError(String message, Exception ex) throws DataBaseException {

		String lmsg = message + " ,due to exception : ";
		// on écrit dans le journal
		LOG.error(lmsg + ex);
		// sans oublié de remonter la stack
		throw new DataBaseException(lmsg + ex);
	}

}
