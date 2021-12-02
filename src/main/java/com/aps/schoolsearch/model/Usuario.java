package com.aps.schoolsearch.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode
@Entity
@Getter
@Setter
public class Usuario {
	
	@NotNull
	@NotEmpty(message="Nome não pode ser vazio")
	@Min(value=3, message="Nome deve ter no mínimo 3 caracteres")
	private String nome;
	
	@Id
	@Column(unique=true)
	@NotEmpty(message="O campo CPF não pode ser vazio")
	@Pattern(regexp="^\\d{3}.\\d{3}.\\d{3}-\\d{2}$", message="Digite um CPF válido, padrão: ___.___.___-__")
	private String cpf;
	
	
	@Column(unique=true)
	@NotNull
	@NotEmpty
	@Pattern(regexp="^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
	@PrimaryKeyJoinColumn
	private Endereco endereco;
	
	@NotNull
	@Column(unique=true)
	@Pattern(regexp="^(\\d{2})(9.)?\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)(9.)____-____")
	private String telefone;
	
	@NotNull(message="Digite/Escolha uma data de nascimento válida, mínimo 18 anos, máximo 01/01/1900")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String dataNascimento;
	
	@NotNull
	private Boolean pne;
	
	@NotNull
	private String sexo;
	
	@NotNull
	@NotEmpty(message="A senha não pode ser vazia")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getPne() {
		return pne;
	}

	public void setPne(Boolean pne) {
		this.pne = pne;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
