package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aps.schoolsearch.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Usuario findByEmail(String email);
	
	Usuario findByCpf(String cpf);

	Usuario findByTelefone(String telefone);
}