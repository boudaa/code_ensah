package com.core.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.core.bll.bo.Student;
import com.core.dao.IStudentDao;
import com.dao.base.hibernate.HibernateGenericDaoImpl;

public class StudentDaoImpl extends HibernateGenericDaoImpl<Student, Long> implements IStudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	public Student getStudentByCne(String pCne) {

		List<Student> students = getEntityByColValue("cne", pCne, "Student");

		if (students != null && !students.isEmpty()) {
			students.get(0);
		}

		return null;
	}

	@Override
	public List<Student> getStudentByName(String pName) {
		List<Student> students = getEntityByColValue("firstName", pName, "Student");

		if (students != null && !students.isEmpty()) {
			return students;
		}

		return new ArrayList<Student>();

	}

}
