package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.bo.Question;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;
import com.dao.QuestionDao;
@Repository
public class QuestionDaoImpl extends HibernateSpringGenericDaoImpl<Question, Long> implements QuestionDao {

	public QuestionDaoImpl() {
		super(Question.class);
	}

}
