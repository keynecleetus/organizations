package com.revature.organizations.exceptions;

public class DBException extends Exception {
	/**method which defines the DBException which is extending from the
	 * parent exception
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException(String message) {
		super(message);
	}

	public DBException(String message, Throwable t) {
		super(message, t);
	}

}
