package com.aps.schoolsearch.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Entity
public class Usuario {
	
	@NotNull
	private String nome;
	
	@Id
	@Pattern(regexp="^\\d{3}.\\d{3}.\\d{3}-\\d{2}$")
	private String cpf;
	
	@NotNull
	private String endereco;
	
	@NotNull
	private String telefone;
	
	@NotNull
	private LocalDate dataNascimento;
	
	@NotNull
	private Boolean pne;
	
	@NotNull
	private String sexo;
	
	@NotNull
	private String nomeUsuario;
	
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
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
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	

}
