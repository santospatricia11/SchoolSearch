package com.aps.schoolsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aps.schoolsearch.model.Role;
import com.aps.schoolsearch.repository.RoleRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void run(String... args) throws Exception {
		if(roleRepository.findByRole("USER") == null) {
			roleRepository.save(new Role("USER"));
		}
		if(roleRepository.findByRole("ADMIN") == null) {
			roleRepository.save(new Role("ADMIN"));
		}
	}

}
