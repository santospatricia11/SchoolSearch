package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.identificacao.PublicacaoId;

public interface PublicacaoRepository extends JpaRepository<Escola, PublicacaoId>{

}
