package com.aps.schoolsearch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

@Entity
@Table(name="escolas")
public class Escola implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7581760189223157052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="escola_id")
	private Long id;
	
	@NotEmpty
	@NotNull
	@Pattern(regexp="^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")
	private String cnpj;

	@NotNull(message="O campo do nome não pode ser nulo.")
	@NotEmpty(message="Nome não pode ser vazio.")
	private String nome;
	
	@Column(unique=true)
	@NotNull(message="O campo do Email não pode ser nulo.")
	@NotEmpty
	@Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Digite um email válido, padrão: _@_._")
	private String email;
	
	@Valid
	@NotNull(message="O campo do endereço não pode ser nulo")
    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER, mappedBy="escola")
	@JoinColumn(name="escola_id", referencedColumnName="escola_id", foreignKey=@ForeignKey(name="escola_endereco_id"))
    @PrimaryKeyJoinColumn
	private EnderecoEscola endereco;

	@NotNull(message="O valor da mensalidade não pode ficar vazio.")
	private BigDecimal mensalidade;
	
	@NotNull(message="O campo do telefone não pode ser nulo")
	@NotEmpty(message="O campo do telefone não pode estar vazio")
	@Column(unique=true)
	@Pattern(regexp="^\\(\\d{2}\\)9\\.\\d{4}-\\d{4}", message="Digite um telefone válido, padrão (__)9.____-____")
	private String telefone;

	@NotNull(message="Você deve escolher a classificação de ensino.")
	@Enumerated(EnumType.STRING)
	private ClassificacaoEnsino classificacaoEnsino;
	
	@ElementCollection(targetClass=NivelEnsino.class)
	@CollectionTable(
			name="nivel_ensino_escola", 
			joinColumns = @JoinColumn(name="escola_id"))
	@JoinTable(
			name="nivel_ensino_escola", 
			joinColumns = @JoinColumn(name="escola_id"))
	@Enumerated(EnumType.STRING)
	@Column(name="nivel_ensino")
	private Set<NivelEnsino> nivelEnsino;

	@NotNull(message="Você deve escolher a classificação de ensino.")
	private MetodoEnsino metodoEnsino;
	

	@NotNull
	@ManyToMany(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
			name="idiomas_escola",
			joinColumns= @JoinColumn(name="escola_id"),
			inverseJoinColumns= @JoinColumn(name="idioma_id")
			)
	private Set<Idioma> linguas;
	
	
	@OneToOne(mappedBy="escola")
	private Usuario administrador;

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

	public EnderecoEscola getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEscola endereco) {
		this.endereco = endereco;
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

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
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

	@Override
	public int hashCode() {
		return Objects.hash(administrador, classificacaoEnsino, cnpj, email, endereco, id, linguas, mensalidade,
				metodoEnsino, nivelEnsino, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escola other = (Escola) obj;
		return Objects.equals(administrador, other.administrador) && classificacaoEnsino == other.classificacaoEnsino
				&& Objects.equals(cnpj, other.cnpj) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id)&& Objects.equals(linguas, other.linguas) 
				&& Objects.equals(mensalidade, other.mensalidade) && metodoEnsino == other.metodoEnsino
				&& Objects.equals(nivelEnsino, other.nivelEnsino) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}
	
	
	
}
