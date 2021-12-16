package com.aps.schoolsearch.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.aps.schoolsearch.model.EnderecoEscola;
import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

public class EscolaPostDto {
	@NotEmpty
	@NotNull
	@Pattern(regexp="^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")
	private String cnpj;

	@NotNull(message="O campo do nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	private String nome;
	
	@Column(unique=true)
	@NotNull(message="O campo do Email não pode ser nulo")
	@NotEmpty
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull
	private EnderecoDto endereco;

	@NotNull
	private BigDecimal mensalidade;

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public BigDecimal getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(BigDecimal mensalidade) {
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

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	
}
