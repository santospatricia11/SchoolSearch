package com.aps.schoolsearch.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Retorna lista de usuários
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = usuarioRepository.findAll();
		
		
		return ResponseEntity.ok().body(list);
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable String id){
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
