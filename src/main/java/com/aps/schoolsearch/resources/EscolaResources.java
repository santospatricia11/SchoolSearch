package com.aps.schoolsearch.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.repository.EscolaRepository;

@RestController
@RequestMapping(value = "/escolas")
public class EscolaResources {
	
	@Autowired
	private EscolaRepository escolaRepository;
	
		@GetMapping
		public ResponseEntity<List<Escola>> findAll() {
			List<Escola> list = escolaRepository.findAll();
			
			return ResponseEntity.ok().body(list);
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<Escola> findById(@PathVariable String id) {//caminho para o id
			Escola esc = escolaRepository.findById(id).get();
			return ResponseEntity.ok().body(esc);
		}	
		
		public Escola save(Escola escola) {
			return escolaRepository.save(escola);
		}
		
		@DeleteMapping("/{escolaId}")
		public ResponseEntity<Void> remover(@PathVariable String id){
			if (!escolaRepository.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			
			escolaRepository.deleteById(id);
			
			return ResponseEntity.noContent().build();
		}
	

}
