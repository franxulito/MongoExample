package com.fran.exception;

public class DAOException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public DAOException() {
		super();
	}
	
	/**
	 * Constructor
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param message
	 * @param e
	 */
	public DAOException(String message, Throwable e) {
		super(message, e);
	}
}
