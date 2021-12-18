package com.aps.schoolsearch.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.aps.schoolsearch.validation.SenhaCorresponde;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SenhaCorresponde
@Validated
public class UsuarioPostDto extends UsuarioDto {

	@NotNull(message = "A senha não pode ser nula")
	@NotEmpty(message = "A senha não pode ser vazia")
	@Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
	private String senha;

	@NotNull(message = "A contrassenha não pode ser nula")
	@NotEmpty(message = "A contrassenha não pode ser vazia")
	@Size(min = 8, message = "A contrassenha deve ter pelo menos 8 caracteres.")
	private String confirmarSenha;

	public String getSenha() {
		return senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}
