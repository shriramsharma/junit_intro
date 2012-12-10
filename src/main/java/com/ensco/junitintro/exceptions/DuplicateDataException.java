/**
 * 
 */
package com.ensco.junitintro.exceptions;

/**
 * @author sharma.shriram
 * 
 */
public class DuplicateDataException extends RuntimeException {

	private String message;

	public DuplicateDataException(String message) {
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
