package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aps.schoolsearch.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Usuario findByEmail(String email);
	
	Usuario findByCpf(String cpf);

	Usuario findByTelefone(String telefone);
}

//value="SELECT id, pgp_sym_decrypt(cpf, 'segredo-43210') as cpf, data_nascimento, email, nome,pne,pgp_sym_decrypt(senha, 'segredo-01234') as senha,sexo, telefone FROM usuario WHERE telefone = :telefone"