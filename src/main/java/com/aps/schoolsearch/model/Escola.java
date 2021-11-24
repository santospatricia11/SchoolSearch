package com.aps.schoolsearch.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class Escola {

	@Id
	@Pattern(regexp=" ")
	private String cnpj;

	@NotNull
	private String nome;

	@NotNull
	private String endereco;

	@NotNull
	private Float mensalidade;

	@NotNull
	private ClassificacaoEnsino classificacaoEnsino;

	@NotNull
	private Set<NivelEnsino> nivelEnsino;

	@NotNull
	private MetodoEnsino metodoEnsino;

	@NotNull
	private List<String> linguas;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Float getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Float mensalidade) {
		this.mensalidade = mensalidade;
	}

	public ClassificacaoEnsino getClassificacaoEnsino() {
		return classificacaoEnsino;
	}

	public void setClassificacaoEnsino(ClassificacaoEnsino classificacaoEnsino) {
		this.classificacaoEnsino = classificacaoEnsino;
	}

	public Set<NivelEnsino> getNivelEnsino() {
		return nivelEnsino;
	}

	public void setNivelEnsino(Set<NivelEnsino> nivelEnsino) {
		this.nivelEnsino = nivelEnsino;
	}

	public MetodoEnsino getMetodoEnsino() {
		return metodoEnsino;
	}

	public void setMetodoEnsino(MetodoEnsino metodoEnsino) {
		this.metodoEnsino = metodoEnsino;
	}

	public List<String> getLinguas() {
		return linguas;
	}

	public void setLinguas(List<String> linguas) {
		this.linguas = linguas;
	}
	
	

}
