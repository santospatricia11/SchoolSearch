package com.aps.schoolsearch.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Pattern(regexp="^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")
	private String cnpj;

	@NotNull
	private String nome;

	@NotNull
	private String endereco;

	@NotNull
	private Float mensalidade;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ClassificacaoEnsino classificacaoEnsino;
	
	@ElementCollection(targetClass=NivelEnsino.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="nivel_ensino_escola", joinColumns = @JoinColumn(name="cnpj_escola", referencedColumnName="cnpj"))
	@Column(name="nivel_ensino")
	@NotNull
	private Set<NivelEnsino> nivelEnsino;

	@NotNull
	private MetodoEnsino metodoEnsino;
	
	@ElementCollection
	@CollectionTable(name="idiomas")
	@Column(name="idioma")
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
