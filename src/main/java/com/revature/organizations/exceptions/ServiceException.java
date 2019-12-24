package com.revature.organizations.exceptions;

public class ServiceException extends Exception {
	/**method which defines the ServiceException which is extending from the
	 * parent exception
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable t) {
		super(message, t);
	}

}
