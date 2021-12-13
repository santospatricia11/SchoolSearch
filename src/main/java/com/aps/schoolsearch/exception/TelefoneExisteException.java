package com.aps.schoolsearch.exception;

public class TelefoneExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8214344472639997749L;
	
	public TelefoneExisteException() {
		super("Esse telefone já foi cadastrado");
	}
	public TelefoneExisteException(String mensagem) {
		super(mensagem);
	}
}
