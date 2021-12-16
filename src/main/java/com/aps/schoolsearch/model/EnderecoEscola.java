package com.aps.schoolsearch.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Table(name="endereco_escola")
public class EnderecoEscola 
extends Endereco
implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8527128068423151579L;

	@Id
    @Column(unique=true, name = "id", nullable=false)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY, optional=false)
    @MapsId
    @JoinColumn(name = "escola_id", foreignKey=@ForeignKey(name="endereco_escola_id"), nullable=false)
    private Escola escola;
    
    /*
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
	*/

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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoEscola other = (EnderecoEscola) obj;
		return Objects.equals(escola, other.escola) && Objects.equals(id, other.id) && super.equals(obj);
	}
	
	
}
