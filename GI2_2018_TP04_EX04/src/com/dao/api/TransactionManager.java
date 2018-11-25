package com.dao.api;

import com.exception.DataBaseException;

public interface TransactionManager {

	public abstract void commit() throws DataBaseException;

	public abstract void rollback() throws DataBaseException;

	public abstract void beginTransaction() throws DataBaseException;

	public void setAutoCommitTrue() throws DataBaseException;
}
