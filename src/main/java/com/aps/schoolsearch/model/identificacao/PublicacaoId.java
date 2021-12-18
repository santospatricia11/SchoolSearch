package com.aps.schoolsearch.model.identificacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;

public class PublicacaoId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3340876943398837111L;

	private Escola escola;

	private Usuario publicadoPor;
	
	private LocalDate dataPublicacao;

    // default constructor

    public PublicacaoId(Escola escola, Usuario publicadoPor, LocalDate dataPublicacao) {
        this.escola=escola;
        this.publicadoPor=publicadoPor;
        this.dataPublicacao=dataPublicacao;
    }

	@Override
	public int hashCode() {
		return Objects.hash(dataPublicacao, escola, publicadoPor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicacaoId other = (PublicacaoId) obj;
		return Objects.equals(dataPublicacao, other.dataPublicacao) && Objects.equals(escola, other.escola)
				&& Objects.equals(publicadoPor, other.publicadoPor);
	}
    
    
}