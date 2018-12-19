package com.core.bll.services;

import com.core.bll.services.impl.StudentManagerServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance  =  new ServiceFactory();
	
	private ServiceFactory() {
	}
	
	public static ServiceFactory getServicesFactory() {
		return instance;
	}
	

	public IStudentMangerService getStudentManagerService() {

		return new StudentManagerServiceImpl();
	}

}
