package com.test;


import com.core.bll.bo.Student;
import com.core.bll.services.IStudentMangerService;
import com.core.bll.services.ServiceFactory;
import com.core.bll.services.impl.StudentManagerServiceImpl;
import com.core.dao.AbstractDaoFactory;
import com.core.dao.IStudentDao;

public class Test {

	public static void main(String[] args) {

	
		IStudentMangerService service  = ServiceFactory.getServicesFactory().getStudentManagerService();
		
			System.out.println("Sauvgarde d'un etudiant");

			Student std = new Student("Karami", "Ali", "karami@ali.com", "189456789");

			Long id = service.addStudent(std);

	

	}

}
