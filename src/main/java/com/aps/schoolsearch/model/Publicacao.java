package com.aps.schoolsearch.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.aps.schoolsearch.model.identificacao.PublicacaoId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PublicacaoId.class)
@EqualsAndHashCode
@Getter
@Setter
public class Publicacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3543708296498516142L;
	
	@Id
	private LocalDate dataPublicacao;
	
	@NotNull
	private String assunto;
	
	@NotNull
	private String conteudo;
	
	@Id
	@OneToOne
	@JoinColumn(name="usuario_id")
	private Usuario publicadoPor;
	
	@Id
	@OneToOne
	@JoinColumn(name="escola_id")
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

	public Usuario getPublicadoPor() {
		return publicadoPor;
	}

	public void setPublicadoPor(Usuario publicadoPor) {
		this.publicadoPor = publicadoPor;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
