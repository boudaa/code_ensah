package com.dao;

import com.bo.Qcm;
import com.genericdao.api.GenericDao;

public interface QcmDao extends  GenericDao<Qcm, Long> {

	public Qcm getMaxQCMScore();

	
}
