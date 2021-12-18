package com.aps.schoolsearch.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Validated
public class EnderecoDto {

    @NotNull(message="O campo do logradouro não pode ser nulo")
    @NotEmpty(message="O campo do logradouro não pode ser vazio")
    @Size(min=10, message="O logradouro deve conter ao menos 10 caracteres")
	private String logradouro;
    
    @NotNull(message="O campo de número não pode estar vazio")
    @Min(value=0, message="O número do logradouro não deve ser negativo")
	private Integer numero;
    
    @NotNull(message="O campo do bairro não pode ser nulo")
    @NotEmpty(message="O campo do logradouro não pode ficar vazio")
    @Size(min=2, message="O bairro deve ter ao menos {min} caracteres")
	private String bairro;
    
    @NotNull(message="O campo da cidade não pode ser nulo")
    @NotEmpty(message="O campo da cidade não pode ficar vazio")
    @Size(min=2, message="A cidade deve ter ao menos {min} caracteres")
	private String cidade;
    
    @NotNull(message="O campo do estado não pode ser nulo")
    @NotEmpty(message="O campo do estado não pode ficar vazio")
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
    
    
}