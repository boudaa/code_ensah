package com.bank.core.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.core.bll.bo.Client;
import com.bank.core.dao.DataAccessLayerException;
import com.bank.core.dao.IClientDao;
import com.bank.core.dao.ObjectNotFoundException;
import com.dao.base.jdbc.JDBCGenericDaoImpl;

/**
 * Classe d'implémentation d'un DAO pour Client
 * 
 * @author Tarik BOUDAA
 * 
 */
public class JdbcClientDAOImpl extends JDBCGenericDaoImpl<Client, Long> implements IClientDao {

	private Connection connect;

	public JdbcClientDAOImpl(Connection pConnect) {
		// on passe au constructeur de la classe de base la classe dao
		super(JdbcClientDAOImpl.class);
		connect = pConnect;
	}

	public Long add(Client pClient) throws DataAccessLayerException {

		Long idPeronne = Long.valueOf(-1);

		// la requete paramétrée
		String lSavePersonne = "INSERT INTO CLIENT "
				+ "   (NOM , PRENOM, CIN, DATENAISSANCE, ADRESSE) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement saveNewClientStatement;

		try {

			saveNewClientStatement = connect.prepareStatement(lSavePersonne, Statement.RETURN_GENERATED_KEYS);

			// On définit les paramétre de la requete paramétrée
			saveNewClientStatement.setString(1, pClient.getNom());
			saveNewClientStatement.setString(2, pClient.getPrenom());
			saveNewClientStatement.setString(3, pClient.getCin());
			saveNewClientStatement.setDate(4, new Date(pClient.getDateNaissance().getTime()));
			saveNewClientStatement.setString(5, pClient.getAdresse());

			// On execute la requete
			int rowCount = saveNewClientStatement.executeUpdate();

			// On récupére la clé primaire auto-generée
			ResultSet results = saveNewClientStatement.getGeneratedKeys();
			if (results.next()) {
				idPeronne = Long.valueOf(results.getLong(1));
			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleSaveError(pClient, ex);

		}
		return idPeronne;

	}

	public void update(Client pClient) throws DataAccessLayerException {

		// requete SQL de mise à jour
		String strUpdateClient = "UPDATE Client SET NOM = ?, PRENOM = ?, CIN = ?, DATENAISSANCE = ?, ADRESSE = ? WHERE ID=?";

		try {

			PreparedStatement stmtUpdateClient = connect.prepareStatement(strUpdateClient);

			stmtUpdateClient.clearParameters();

			stmtUpdateClient.setString(1, pClient.getNom());
			stmtUpdateClient.setString(2, pClient.getPrenom());
			stmtUpdateClient.setString(3, pClient.getCin());
			stmtUpdateClient.setDate(4, new Date(pClient.getDateNaissance().getTime()));
			stmtUpdateClient.setString(5, pClient.getAdresse());
			stmtUpdateClient.setLong(6, pClient.getId());

			stmtUpdateClient.executeUpdate();

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleUpdateError(pClient, ex);
		}

	}

	public Client find(Long pId) throws DataAccessLayerException, ObjectNotFoundException {

		// requete paramétrèe permet d'avoir un client par son id
		String lGetClient = "SELECT * FROM CLIENT WHERE id = ?";

		Client client = null;

		try {

			PreparedStatement stmtGetClient = connect.prepareStatement(lGetClient);
			stmtGetClient.clearParameters();
			stmtGetClient.setLong(1, Long.valueOf(pId));

			ResultSet result = stmtGetClient.executeQuery();

			// Si trouvé alors conversion du resultset on un objet metier
			if (result.next()) {

				// on récupère les résultat de la requete

				String lNom = result.getString("NOM");
				String lPrenom = result.getString("PRENOM");
				String lCIn = result.getString("CIN");
				Date lDateNaissance = result.getDate("DATENAISSANCE");
				String lAdrssse = result.getString("ADRESSE");

				// On construit un objet de type client

				client = new Client(lCIn, lNom, lPrenom, lAdrssse, lDateNaissance);
			} else {
				throw new ObjectNotFoundException("Object with id=" + pId + " is not found in database");
			}
		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleFindError(pId, ex);

		}

		return client;
	}

	public void delete(Long pId) throws DataAccessLayerException {

		// requete pour la suppression d'un client
		String strDeleteClient = "DELETE FROM CLIENT WHERE ID = ?";

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

	public List<Client> getAll() throws DataAccessLayerException {
		// requete paramétrée permet d'avoir un compte par son numéro
		String lGetCompte = "SELECT * FROM COMPTE";

		List<Client> list = new ArrayList<Client>();

		try {

			PreparedStatement stmtGetCompte = connect.prepareStatement(lGetCompte);

			ResultSet result = stmtGetCompte.executeQuery();
			while (result.next()) {

				String lNom = result.getString("NOM");
				String lPrenom = result.getString("PRENOM");
				String lCIn = result.getString("CIN");
				Date lDateNaissance = result.getDate("DATENAISSANCE");
				String lAdrssse = result.getString("ADRESSE");

				list.add(new Client(lCIn, lNom, lPrenom, lAdrssse, lDateNaissance));

			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleFindAllError(ex);

		}

		return list;
	}

}
