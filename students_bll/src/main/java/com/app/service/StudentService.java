package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Student;


public interface StudentService {

	void addStudent(Student pStudent);

	List<Student> getAllStudents();

}
