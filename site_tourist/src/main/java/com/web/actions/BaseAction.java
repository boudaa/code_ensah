package com.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * a basic class for struts action, define some useful methods to access session
 * and request objects
 * 
 * @author BOUDAA
 * 
 */
public abstract class BaseAction extends ActionSupport {

	/** Constante MESSAGES */
	protected static final String MESSAGES = "messages";

	/**
	 * puts a message in the session
	 * 
	 * @param pMsg
	 *            The message to put in http session
	 */

	protected void saveMessageInSession(String pMsg) {

		// We get the session
		HttpSession session = getSession();

		// We get messages stored in the http session
		List<String> lMessages = (List<String>) session.getAttribute(MESSAGES);

		// If there is no messages in the session
		if (lMessages == null) {
			// initialize the message collection
			lMessages = new ArrayList<String>();
		}

		// We add a message in the collection
		lMessages.add(pMsg);

		// store the collection in the session
		session.setAttribute(MESSAGES, lMessages);
	}

	
	
	/**
	 * puts a message in the request
	 * 
	 * @param pMsg
	 *            The message to put in http request
	 */

	protected void saveMessageInRequest(String pMsg) {

		// We get the request
		HttpServletRequest  rq=  getRequest()  ;

		// We get messages stored in the http session
		List<String> lMessages = (List<String>) rq.getAttribute(MESSAGES);

		// If there is no messages in the request
		if (lMessages == null) {
			// initialize the message collection
			lMessages = new ArrayList<String>();
		}

		// We add a message in the collection
		lMessages.add(pMsg);

		// store the collection in the request
		rq.setAttribute(MESSAGES, lMessages);
	}
	
	/**
	 * allows the access to the http request object
	 * 
	 * @return returns the current request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * allows the access to the http response object
	 * 
	 * @return return the current response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * gests the user session
	 * 
	 * @return user session
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
}
