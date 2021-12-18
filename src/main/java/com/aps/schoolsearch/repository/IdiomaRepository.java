package com.aps.schoolsearch.repository;

import org.springframework.data.repository.CrudRepository;

import com.aps.schoolsearch.model.Idioma;

public interface IdiomaRepository extends CrudRepository<Idioma, Long> {
	
	Idioma findByLingua(String lingua);
	
}
