package com.aps.schoolsearch.repository;

import org.springframework.data.repository.CrudRepository;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;

public interface EscolaRepository extends CrudRepository<Escola, Long>{
	Escola findByEmail(String email);
	
	Escola findByCnpj(String cnpj);
	
	Escola findByAdministrador(Usuario administrador);
	
	Escola findByTelefone(String telefone);
	
}
