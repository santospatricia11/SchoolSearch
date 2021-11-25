package com.aps.schoolsearch.model.identificacao;

import java.io.Serializable;
import java.time.LocalDate;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PublicacaoId implements Serializable {
	
	private Escola escola;

	private Usuario publicadoPor;
	
	private LocalDate dataPublicacao;

    // default constructor

    public PublicacaoId(Escola escola, Usuario publicadoPor, LocalDate dataPublicacao) {
        this.escola=escola;
        this.publicadoPor=publicadoPor;
        this.dataPublicacao=dataPublicacao;
    }

}