package com.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.dao.StudentDao;
import com.bo.Student;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;

@Repository
public class StudentDaoImpl extends HibernateSpringGenericDaoImpl<Student, Long> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

}
