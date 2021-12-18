package com.aps.schoolsearch.exception;

public class EmailExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8254515598139758243L;
	
	public EmailExisteException() {
		super("Esse email já foi cadastrado");
	}
	public EmailExisteException(String mensagem) {
		super(mensagem);
	}
}
