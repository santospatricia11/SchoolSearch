package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aps.schoolsearch.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
