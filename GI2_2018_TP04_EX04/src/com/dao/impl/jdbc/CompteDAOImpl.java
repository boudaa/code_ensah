package com.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bo.Client;
import com.bo.Compte;
import com.dao.DaoFactory;
import com.dao.interfaces.CompteDao;
import com.exception.DataBaseException;
import com.exception.ObjectNotFoundException;

/**
 * Classe qui offre les services de gestion des comptes dans la base données
 * 
 * @author Tarik BOUDAA
 * 
 */

/**
 * Classe d'implémentation d'un DAO pour Client
 * 
 * @author Tarik BOUDAA
 * 
 */
public class CompteDAOImpl extends GdbcDaoBase implements CompteDao {

	private Connection connect;

	public CompteDAOImpl(Connection pConnect) {
		// on passe au constructeur de la classe de base la classe dao
		super(CompteDAOImpl.class);
		connect = pConnect;
	}

	public Long add(Compte pCompte) throws DataBaseException {

		Long idCompte = Long.valueOf(-1);

		// la requete paramétrée

		String lSaveCompte = "INSERT INTO COMPTE " + "   ( solde, maxDecouvert, maxDebit, idPersonne) "
				+ "VALUES (?, ?, ?, ?)";

		PreparedStatement saveNewClientStatement;

		try {

			// on commence par persister le client
			Client lClient = pCompte.getTitulaire();
			ClientDAOImpl lDaoClient = (ClientDAOImpl) DaoFactory.getDao("com.dao.impl.jdbc.ClientDAOImpl");
			Long idClient = lDaoClient.add(lClient);

			// on persiste le compte
			PreparedStatement saveNewCompteStatement = connect.prepareStatement(lSaveCompte,
					Statement.RETURN_GENERATED_KEYS);
			saveNewCompteStatement.setDouble(1, pCompte.getSolde());
			saveNewCompteStatement.setDouble(2, pCompte.getDecouvertMax());
			saveNewCompteStatement.setDouble(3, pCompte.getDebitMax());
			saveNewCompteStatement.setLong(4, idClient);

			saveNewCompteStatement.executeUpdate();

			ResultSet results = saveNewCompteStatement.getGeneratedKeys();
			if (results.next()) {
				idCompte = Long.valueOf(results.getInt(1));
			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleSaveError(pCompte, ex);

		}
		return idCompte;

	}

	public void update(Compte pCompte) throws DataBaseException {

		String strUpdateCompte = "UPDATE Compte SET SOLDE = ?, MAXDECOUVERT = ?, MAXDEBIT = ? WHERE ID =?";

		try {
			PreparedStatement stmtUpdateCompte = connect.prepareStatement(strUpdateCompte);

			stmtUpdateCompte.clearParameters();

			stmtUpdateCompte.setDouble(1, pCompte.getSolde());
			stmtUpdateCompte.setDouble(2, pCompte.getDecouvertMax());
			stmtUpdateCompte.setDouble(3, pCompte.getDebitMax());
			stmtUpdateCompte.setLong(4, pCompte.getNum());

			stmtUpdateCompte.executeUpdate();

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleUpdateError(pCompte, ex);
		}

	}

	public Compte find(Long pNum) throws DataBaseException, ObjectNotFoundException {

		// requete paramétrée permet d'avoir un compte par son numéro
		String lGetCompte = "SELECT * FROM COMPTE WHERE ID = ?";

		Compte compte = null;

		try {

			PreparedStatement stmtGetCompte = connect.prepareStatement(lGetCompte);
			stmtGetCompte.clearParameters();
			stmtGetCompte.setLong(1, pNum);

			ResultSet result = stmtGetCompte.executeQuery();
			if (result.next()) {

				Long lNum = result.getLong("ID");
				double lSolde = result.getDouble("SOLDE");
				double lMaxdecouvert = result.getDouble("MAXDECOUVERT");
				double lMaxDebitAutorise = result.getDouble("MAXDEBIT");
				long lIdPersonne = result.getInt("IDPERSONNE");
				ClientDAOImpl lDaoClient = (ClientDAOImpl) DaoFactory.getDao("com.dao.impl.jdbc.ClientDAOImpl");
				Client lTitulaire = lDaoClient.find(Long.valueOf(lIdPersonne));

				compte = new Compte(lNum, lSolde, lTitulaire, lMaxdecouvert, lMaxDebitAutorise);
			}

			else {
				throw new ObjectNotFoundException("Object with id=" + pNum + " is not found in database");
			}
		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleFindError(pNum, ex);

		}

		return compte;
	}

	public void delete(Long pId) throws DataBaseException {

		// requete pour la suppression d'un client
		String strDeleteClient = "DELETE FROM COMPTE WHERE ID = ?";

		try {

			PreparedStatement stmtDeleteClient = connect.prepareStatement(strDeleteClient);

			stmtDeleteClient.clearParameters();
			stmtDeleteClient.setLong(1, Long.valueOf(pId));
			stmtDeleteClient.executeUpdate();

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleDeleteError(pId, ex);
		}

	}

	public List<Compte> getAll() throws DataBaseException {
		// requete paramétrée permet d'avoir un compte par son numéro
		String lGetCompte = "SELECT * FROM COMPTE";

		List<Compte> list = new ArrayList<Compte>();

		try {

			PreparedStatement stmtGetCompte = connect.prepareStatement(lGetCompte);

			ClientDAOImpl lDaoClient = (ClientDAOImpl) DaoFactory.getDao("com.dao.impl.jdbc.ClientDAOImpl");
			ResultSet result = stmtGetCompte.executeQuery();
			while (result.next()) {

				Long lNum = result.getLong("ID");
				double lSolde = result.getDouble("SOLDE");
				double lMaxdecouvert = result.getDouble("MAXDECOUVERT");
				double lMaxDebitAutorise = result.getDouble("MAXDEBIT");
				long lIdPersonne = result.getInt("IDPERSONNE");

				try {
					list.add(new Compte(lNum, lSolde, lDaoClient.find(Long.valueOf(lIdPersonne)), lMaxdecouvert,
							lMaxDebitAutorise));
				} catch (ObjectNotFoundException e) {
					// normalement il doit pas y avoir des comptes sans titulaire en base de
					// données, alors si cas de bug on retourne des compte avec titulaire null
					list.add(new Compte(lNum, lSolde, null, lMaxdecouvert, lMaxDebitAutorise));
				}
			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleFindAllError(ex);

		}

		return list;
	}

}
