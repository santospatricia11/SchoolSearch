package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aps.schoolsearch.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	@Query(nativeQuery=true, value="SELECT * FROM escola WHERE email = :email")
	Usuario findByEmail(@Param("email") String email);
}
