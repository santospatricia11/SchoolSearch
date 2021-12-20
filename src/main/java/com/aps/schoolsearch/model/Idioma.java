package com.aps.schoolsearch.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="idioma")
public class Idioma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7225624323240035500L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idioma_id")
	private Long id;
	
	@Column(name="lingua", unique=true)
	@NotNull
	@NotEmpty
	private String lingua;
	
	public Idioma() {
	}
	public Idioma(String lingua) {
		setLingua(lingua);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lingua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Idioma other = (Idioma) obj;
		return Objects.equals(id, other.id) && Objects.equals(lingua, other.lingua);
	}
	
}
