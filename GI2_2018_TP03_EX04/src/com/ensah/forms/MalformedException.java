package com.ensah.forms;

public class MalformedException extends Exception {

	public MalformedException(String pMsg) {
		super(pMsg);
	}

	public MalformedException(String pMsg, Throwable th) {
		super(pMsg, th);
	}

}
