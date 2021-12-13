package com.aps.schoolsearch.model.categorizacao;

public enum MetodoEnsino {
	
	CONSTRUTIVISMO("Construtivismo"), 
	MONTESSORI("Montessori"), 
	WALDORF("Waldorf"), 
	PIKLER("Pikler"), 
	TRADICIONAL("Tradicional");
	
	private String metodo;
	
	MetodoEnsino(String metodo) {
		this.metodo = metodo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
}
