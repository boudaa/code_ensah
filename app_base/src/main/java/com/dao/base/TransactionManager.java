package com.dao.base;

import com.dao.base.exceptions.DataAccessLayerException;

/**
 * Cette interface définit d'une manière abstraite les services d'un
 * gestionnaire de transaction
 * 
 * @author boudaa
 *
 */
public interface TransactionManager {

	public abstract void commit() throws DataAccessLayerException;

	public abstract void rollback() throws DataAccessLayerException;

	public abstract void beginTransaction() throws DataAccessLayerException;

	public void setAutoCommitTrue() throws DataAccessLayerException;
}
