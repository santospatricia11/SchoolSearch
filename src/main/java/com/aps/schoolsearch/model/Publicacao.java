package com.aps.schoolsearch.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class Publicacao {
	
	@Id
	@Pattern(regexp=" ")
	private LocalDate dataPublicacao;
	
	@NotNull
	private String assunto;
	
	@NotNull
	private String conteudo;
	
	@Id
	@Pattern(regexp=" ")
	private Usuario publicadoPorUsuario;
	
	@Id
	@Pattern(regexp=" ")
	private Escola escola;

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Usuario getPublicadoPorUsuario() {
		return publicadoPorUsuario;
	}

	public void setPublicadoPorUsuario(Usuario publicadoPorUsuario) {
		this.publicadoPorUsuario = publicadoPorUsuario;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	
	

}
