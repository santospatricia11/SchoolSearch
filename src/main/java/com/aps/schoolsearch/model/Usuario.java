package com.aps.schoolsearch.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

import com.aps.schoolsearch.model.dto.UsuarioDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode
@Entity
@Getter
@Setter
//@SenhaCorresponde
public class Usuario {

	@NotNull(message="O campo do nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	@Size(min=3, message="Nome deve ter no mínimo 3 caracteres")
	private String nome;
	
	@Id
	@NotNull(message="O campo do CPF não pode ser nulo")
	@Column(unique=true, name="cpf") 
	@NotEmpty(message="O campo CPF não pode ser vazio")
	@Pattern(regexp="^\\d{3}.\\d{3}.\\d{3}-\\d{2}$", message="Digite um CPF válido, padrão: ___.___.___-__")
	@ColumnTransformer(
		    read =  "PGP_SYM_DECRYPT(cpf,'senha-secreta-0123456789')",
		    write = "PGP_SYM_ENCRYPT(?,'senha-secreta-0123456789')"
		    )
	private String cpf;
	
	
	@Column(unique=true)
	@NotNull(message="O campo do Email não pode ser nulo")
	@NotEmpty
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull(message="O campo do endereço não pode ser nulo")
    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="usuario")
	@JoinColumn(name="cpf", referencedColumnName="cpf", foreignKey=@ForeignKey(name="usuario_endereco_id"))
    @PrimaryKeyJoinColumn
	private Endereco endereco;
	
	@NotNull(message="O campo do telefone não pode ser nulo")
	@NotEmpty(message="O campo do telefone não pode estar vazio")
	@Column(unique=true)
	@Pattern(regexp="^\\(\\d{2}\\)9\\.\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)9.____-____")
	private String telefone;
	
	@NotNull(message="Digite/Escolha uma data de nascimento válida, mínimo 18 anos, máximo 01/01/1900")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Pattern(regexp="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$", message="Digite uma data de nascimento válida")
	private String dataNascimento;
	
	@NotNull
	private Boolean pne;
	
	@NotNull
	private String sexo;
	
	@NotNull
	@NotEmpty(message="A senha não pode ser vazia")
	@Size(min=8, message="A senha deve ter pelo menos 8 caracteres.")
	private String senha;
	
	public Usuario() { } //contrutor padrão

	public Usuario(UsuarioDto usuarioDto) {
		setCpf(usuarioDto.getCpf());
		setDataNascimento(usuarioDto.getDataNascimento());
		setEmail(usuarioDto.getEmail());
		setEndereco(new Endereco(usuarioDto.getEndereco()));
		setNome(usuarioDto.getNome());
		setPne(usuarioDto.getPne());
		setSenha(usuarioDto.getSenha());
		setTelefone(usuarioDto.getTelefone());
		setSexo(usuarioDto.getSexo());
	}

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
