package com.aps.schoolsearch.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2868699869225799614L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	
	@NotNull(message="O campo do CPF não pode ser nulo")
	@NotEmpty(message="O campo CPF não pode ser vazio")
//	/*
	@ColumnTransformer(
			forColumn="cpf",
            read = "PGP_SYM_DECRYPT(cpf,'segredo-43210')",
            write = "PGP_SYM_ENCRYPT(?, 'segredo-43210')"
    )
//  */
	@Column(unique=true,
		name="cpf", 
		columnDefinition="bytea",
		nullable=false)

	private String cpf;
	
	@NotNull(message="O campo do nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	@Size(min=3, message="Nome deve ter no mínimo 3 caracteres")
	private String nome;
	
	@Column(unique=true)
	@NotNull(message="O campo do Email não pode ser nulo")
	@NotEmpty
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull(message="O campo do endereço não pode ser nulo")
    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER, mappedBy="usuario")
	@JoinColumn(name="usuario_id", referencedColumnName="id", foreignKey=@ForeignKey(name="usuario_endereco_id"))
    @PrimaryKeyJoinColumn
	private EnderecoUsuario endereco;
	
	@NotNull(message="O campo do telefone não pode ser nulo")
	@NotEmpty(message="O campo do telefone não pode estar vazio")
	@Column(unique=true)
	@Pattern(regexp="^\\(\\d{2}\\)9\\.\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)9.____-____")
	private String telefone;
	
	@NotNull(message="Digite/Escolha uma data de nascimento válida, mínimo 18 anos, máximo 01/01/1900")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="data_nascimento", columnDefinition="DATE")
	private LocalDate dataNascimento;
	
	@NotNull
	private Boolean pne;
	
	@NotNull
	private String sexo;
	
	@NotNull
	@NotEmpty(message="A senha não pode ser vazia")
	@Size(min=8, message="A senha deve ter pelo menos 8 caracteres.")
//	/*
	@ColumnTransformer(
			forColumn="senha",
            read = "PGP_SYM_DECRYPT(senha, 'segredo-01234')",
            write = "PGP_SYM_ENCRYPT(?, 'segredo-01234')"
    )
//    */
	@Column(name="senha", 
		columnDefinition="bytea",
		nullable=false)
	
	private String senha;
	
	@NotNull(message="A role não pode ficar vazia")
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName="usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName="role_id")
    )
	private Set<Role> roles = new HashSet<>();
	
	@JoinColumn(name="escola_id", nullable=true, referencedColumnName = "escola_id")
	@OneToOne
	private Escola escola;
	
	
	public Usuario() {//contrutor padrão
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

	public EnderecoUsuario getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoUsuario endereco) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Escola getEscola() {
		return escola;
	}


	public void setEscola(Escola escola) {
		this.escola = escola;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, id, telefone);
	}
	
	
}
