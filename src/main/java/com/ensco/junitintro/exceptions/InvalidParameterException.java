/**
 * 
 */
package com.ensco.junitintro.exceptions;

/**
 * @author sharma.shriram
 * 
 */
public class InvalidParameterException extends RuntimeException {

	private String message;

	public InvalidParameterException(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
