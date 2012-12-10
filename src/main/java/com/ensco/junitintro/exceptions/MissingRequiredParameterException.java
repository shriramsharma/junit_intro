/**
 * 
 */
package com.ensco.junitintro.exceptions;

/**
 * @author sharma.shriram
 * 
 */
public class MissingRequiredParameterException extends RuntimeException {

	private String message;

	public MissingRequiredParameterException(String message) {
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
