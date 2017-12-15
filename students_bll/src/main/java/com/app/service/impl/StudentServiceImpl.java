package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.StudentDao;
import com.app.service.StudentService;
import com.bo.Student;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	public void addStudent(Student pStudent) {

		studentDao.create(pStudent);

	}

	@Transactional(readOnly = true)
	public List<Student> getAllStudents() {
		return studentDao.getAll();
	}

}
