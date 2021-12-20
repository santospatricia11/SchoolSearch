package com.aps.schoolsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aps.schoolsearch.model.Idioma;
import com.aps.schoolsearch.model.Role;
import com.aps.schoolsearch.repository.IdiomaRepository;
import com.aps.schoolsearch.repository.RoleRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;
import com.aps.schoolsearch.service.IdiomaService;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IdiomaRepository idiomaRepository;
	
	@Autowired
	private IdiomaService idiomaService;
	
	public void run(String... args) throws Exception {
		// se não houver roles cadastradas
		if(!roleRepository.findAll().iterator().hasNext()) {
			roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("ADMIN"));
		}
		if(!idiomaRepository.findAll().iterator().hasNext()) {
			idiomaService.registrarIdioma("Português Brasileiro");
		}
	}

}
