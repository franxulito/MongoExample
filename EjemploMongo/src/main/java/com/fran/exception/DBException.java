package com.fran.exception;

public class DBException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public DBException() {
		super();
	}
	
	/**
	 * Constructor
	 * @param message
	 */
	public DBException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param message
	 * @param e
	 */
	public DBException(String message, Throwable e) {
		super(message, e);
	}
}
