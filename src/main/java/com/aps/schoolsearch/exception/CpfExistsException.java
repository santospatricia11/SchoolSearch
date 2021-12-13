package com.aps.schoolsearch.exception;

public class CpfExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5777218493017974197L;
	
	
	public CpfExistsException() {
		super("O CPF já existe no sistema.");
	} 
	public CpfExistsException(String mensagem) {
		super(mensagem);
	}
}
