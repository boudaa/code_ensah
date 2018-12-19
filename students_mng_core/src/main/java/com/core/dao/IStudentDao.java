package com.core.dao;

import java.util.List;

import com.core.bll.bo.Student;
import com.dao.base.IGenericDao;

public interface IStudentDao extends IGenericDao<Student, Long> {

	public Student getStudentByCne(String pCne);

	public List<Student> getStudentByName(String pName);

}
