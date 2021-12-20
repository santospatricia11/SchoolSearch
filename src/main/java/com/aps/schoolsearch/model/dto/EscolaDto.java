package com.aps.schoolsearch.model.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.aps.schoolsearch.model.Idioma;
import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

public class EscolaDto {

	private Long id;
	
	@NotEmpty(message="O CNPJ não pode ficar vazio")
	@NotNull
	@Pattern(regexp="^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message="O cnpj deve seguir o padrão 00.000.000/0000-00")
	private String cnpj;

	@NotNull(message="O campo do nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	@Size(min=10, message="O nome da escola deve ter ao menos {min} caracteres.")
	private String nome;
	
	@NotNull(message="O campo do Email não pode ser nulo")
	@NotEmpty(message="O campo do email não pode ficar vazio")
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull
	private EnderecoDto endereco;

	@NotNull(message="A mensalidade não pode ficar vazia")
	@Min(0)
	private BigDecimal mensalidade;
	
    @NotNull(message="O campo do telefone não pode ser nulo")
	@NotEmpty(message="O campo do telefone não pode estar vazio")
	@Pattern(regexp="^\\(\\d{2}\\)9\\.\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)9.____-____")
	private String telefone;

    @NotNull(message="Você deve escolher a classificação de ensino.")
	@Enumerated(EnumType.STRING)
	private ClassificacaoEnsino classificacaoEnsino;
	
	@NotNull
	@NotEmpty(message="Você deve escolher no mínimo 1 dos níveis de ensino.")
	private Set<NivelEnsino> nivelEnsino;

	@NotNull(message="Você deve indicar o método de ensino utilizado.")
	private MetodoEnsino metodoEnsino;
	
	private Set<Idioma> linguas;

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

	public Set<Idioma> getLinguas() {
		return linguas;
	}

	public void setLinguas(Set<Idioma> linguas) {
		this.linguas = linguas;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
