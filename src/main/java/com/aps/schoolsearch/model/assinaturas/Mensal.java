package com.aps.schoolsearch.model.assinaturas;

import java.util.Date;

public class Mensal implements AssinaturaEscola{
	private Integer id;
	private Double valor;
	private Date dataInicio;
	private Date dataFim;
	private String descricao;
	
	@Override
	public Double pagar() {
		// TODO Auto-generated method stub
		return null;
	}

}
