package com.aps.schoolsearch.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aps.schoolsearch.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query(nativeQuery=true, value="SELECT * FROM roles WHERE nome = :nome")
	Role findByRole(@Param("nome") String nome);
}

