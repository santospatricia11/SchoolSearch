package com.aps.schoolsearch.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Endereco {
	@NotNull
    @NotEmpty(message="O campo do logradouro não pode ser vazio")
    @Size(min=10, message="O logradouro deve conter ao menos 10 caracteres")
	private String logradouro;
    
    @NotNull(message="O campo de nível não pode estar vazio")
    @Min(value=0, message="O número do logradouro não deve ser negativo")
	private Integer numero;
    
    @NotNull
    @NotEmpty
    @Size(min=2, message="O bairro deve ter ao menos {min} caracteres")
	private String bairro;
    
    @NotNull
    @NotEmpty
    @Size(min=2, message="A cidade deve ter ao menos {min} caracteres")
	private String cidade;
    @NotNull
    @NotEmpty
	private String estado;
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, estado, logradouro, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(estado, other.estado) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(numero, other.numero);
	}
    
    
}
