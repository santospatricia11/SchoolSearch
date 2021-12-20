package com.aps.schoolsearch.service;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aps.schoolsearch.model.Idioma;
import com.aps.schoolsearch.repository.IdiomaRepository;

@Service
@Transactional
public class IdiomaService {
	@Autowired
	private IdiomaRepository idiomaRepository;
	
	
	public Idioma registrarIdioma(String lingua) {
		lingua = normalizar(lingua);
		Idioma idioma = findByLingua(lingua);
		if(idioma == null) {
			idioma = new Idioma(lingua);
			idiomaRepository.save(idioma);
		}
		
		return idioma;
	}
	
	public Idioma findByLingua(String lingua) {
		String normal = normalizar(lingua);
		return idiomaRepository.findByLingua(normal);
	}


	private String normalizar(String lingua) {
		String[] partes = lingua.split(" ");
		
		return Arrays.stream(partes)
				.map(String::toUpperCase)
				.reduce(
						(acumulador, entrada) -> {return String.format("%s %s", acumulador, entrada);}).get();
		
	}
	
	
}
