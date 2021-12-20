package com.aps.schoolsearch.model.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.validation.IdadeCorreta;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Validated
public class UsuarioDto {
	
	@NotNull(message="O campo do nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	@Size(min=3, message="Nome deve ter no mínimo 3 caracteres")
	private String nome;

	@NotNull(message="O campo do CPF não pode ser nulo")
	@NotEmpty(message="O campo CPF não pode ser vazio")
	@Pattern(regexp="^\\d{3}.\\d{3}.\\d{3}-\\d{2}$", message="CPF fora do padrão. ex: 111.111.111-11")
	@Size(min=14, max=14, message="O campo do CPF deve ter exatamente 14 caracteres o que inclui os pontos e traços. Ex: 000.000.000-00")
	private String cpf;
	
	@NotNull(message="O campo do Email não pode ser nulo")
	@NotEmpty(message="O campo do email não pode ficar vazio")
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull(message="O campo do endereço não pode ser nulo")
	private EnderecoDto endereco;
	
	@NotNull(message="O campo do telefone não pode ser nulo")
	@NotEmpty(message="O campo do telefone não pode estar vazio")
	@Pattern(regexp="^\\(\\d{2}\\)9\\.\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)9.____-____")
	private String telefone;
	
	@IdadeCorreta
	@NotNull(message="Digite/Escolha uma data de nascimento válida, mínimo 18 anos, máximo 01/01/1900")
	@DateTimeFormat(pattern="dd/MM/yyyy")
//	@Pattern(regexp="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$", message="Digite uma data de nascimento válida")
	private LocalDate dataNascimento;
	
	@NotNull
	private Boolean pne;
	
	@NotNull
	private String sexo;
	
	private EscolaDto escola;
	
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

}
