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

@Entity
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
