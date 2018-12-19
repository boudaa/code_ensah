package com.core.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.core.bll.bo.Student;
import com.core.dao.IStudentDao;
import com.dao.base.exceptions.DataAccessLayerException;
import com.dao.base.exceptions.ObjectNotFoundException;
import com.dao.base.jdbc.JDBCGenericDaoImpl;

public class StudentDaoImpl extends JDBCGenericDaoImpl<Student, Long> implements IStudentDao {

	private Connection connect;

	public StudentDaoImpl(Connection pConnexion) {

		super(Student.class);

		pConnexion = connect;
	}

	@Override
	public Long add(Student pStudent) throws DataAccessLayerException {
		Long idStudent = Long.valueOf(-1);

		// la requete parametres

		String lSaveCompte = "INSERT INTO ETUDIANT " + "   ( firstName, lastName, email, cne) " + "VALUES (?, ?, ?, ?)";

		try {

			// on persiste le compte
			PreparedStatement saveStudentStm = connect.prepareStatement(lSaveCompte, Statement.RETURN_GENERATED_KEYS);
			saveStudentStm.setString(1, pStudent.getFirstName());
			saveStudentStm.setString(2, pStudent.getLastName());
			saveStudentStm.setString(3, pStudent.getEmail());
			saveStudentStm.setString(4, pStudent.getCne());

			saveStudentStm.executeUpdate();

			ResultSet results = saveStudentStm.getGeneratedKeys();
			if (results.next()) {
				idStudent = Long.valueOf(results.getInt(1));
			}

		} catch (SQLException ex) {

			// tracer et remonter la stack
			handleSaveError(pStudent, ex);

		}
		return idStudent;
	}

	@Override
	public void delete(Long id) throws DataAccessLayerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Student obj) throws DataAccessLayerException {
		// TODO Auto-generated method stub

	}

	@Override
	public Student find(Long id) throws DataAccessLayerException, ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAll() throws DataAccessLayerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getEntityByColValue(String pColName, String pColVal, String pClassName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByCne(String pCne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentByName(String pName) {
		// TODO Auto-generated method stub
		return null;
	}

}
