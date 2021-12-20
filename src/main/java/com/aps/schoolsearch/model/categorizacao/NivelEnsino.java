package com.aps.schoolsearch.model.categorizacao;

public enum NivelEnsino {
	
	INFANTIL("Infantil"), 
	FUNDAMENTAL_1("Fundamental 1"), 
	FUNDAMENTAL_2("Fundamental 2"), 
	ENSINO_MEDIO("Ensino Médio");
	
	private String nivel;
	
	NivelEnsino(String nivel){
		this.nivel = nivel;
	}
	
	public String getNivel() {
		return nivel;
	}
	@Override
	public String toString() {
		return nivel;
	}
}
