package com.revature.organizations.exceptions;

public class ValidatorException  extends Exception {
	/**method which defines the ValidatorException which is extending from the
	 * parent exception
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(String message, Throwable t) {
		super(message, t);
}
}
