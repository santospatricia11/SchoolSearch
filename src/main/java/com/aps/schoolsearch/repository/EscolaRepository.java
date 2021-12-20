package com.aps.schoolsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;

public interface EscolaRepository extends JpaRepository<Escola, Long>{
	Escola findByEmail(String email);
	
	Escola findByCnpj(String cnpj);
	
	Escola findByAdministrador(Usuario administrador);
	
	Escola findByTelefone(String telefone);
	
}
