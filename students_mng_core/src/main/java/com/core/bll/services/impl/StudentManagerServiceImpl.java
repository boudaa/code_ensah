package com.core.bll.services.impl;

import com.core.bll.bo.Student;
import com.core.bll.services.IStudentMangerService;
import com.core.dao.AbstractDaoFactory;
import com.core.dao.IStudentDao;

public class StudentManagerServiceImpl implements  IStudentMangerService{

	
	private IStudentDao studentDao;
	
	
	public StudentManagerServiceImpl() {

		studentDao =  AbstractDaoFactory.getFactory().getStudentDao();
	}
	
	
	public Long addStudent(Student pStudent) {
		
		//TODO : verifier/apliquer les regles metiers
		
		
		return  studentDao.add(pStudent);
	}
	
	

}
