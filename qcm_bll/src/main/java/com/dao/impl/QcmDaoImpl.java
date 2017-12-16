package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bo.Qcm;
import com.genericdao.impl.HibernateSpringGenericDaoImpl;
import com.dao.QcmDao;
@Repository
public class QcmDaoImpl extends HibernateSpringGenericDaoImpl<Qcm, Long> implements QcmDao {

	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public QcmDaoImpl() {
		super(Qcm.class);
	}

	public Qcm getMaxQCMScore() {

		String HqlQuery = "select q from Qcm q where q.score ="
				+ "(select max(qq.score) from Qcm qq )";

		List l = hibernateTemplate.find(HqlQuery);
		if (l == null || l.size() == 0)
			throw new ObjectRetrievalFailureException(QcmDaoImpl.class, null);

		return (Qcm) l.get(0);
	}

}
