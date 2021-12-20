package com.aps.schoolsearch.model.categorizacao;

public enum ClassificacaoEnsino {
	
	PRIVADA("Privada"), 
	COMUNITARIA("Comunitária"), 
	FILANTROPICA("Filantrópica"), 
	CONFESSIONAL("Confessional");
	
	private String classificacao;
	
	ClassificacaoEnsino(String classificacao){
		this.classificacao = classificacao;
	}
	
	public String getClassificacao() {
		return classificacao;
	}
	
	@Override
	public String toString() {
		return classificacao;
	}

}
