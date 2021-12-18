package com.aps.schoolsearch.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.repository.EscolaRepository;

/*
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
			Optional<Escola> result = escolaRepository.findById(id);
			
			if(result.isPresent()) {
				return ResponseEntity.ok().body(result.get());
			}
			return ResponseEntity.notFound().build();
			
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
	
		@PostMapping("/pesquisarescola")
		public ModelAndView pesquisarEscola(@RequestParam("nomepesquisa") String nomepesquisa) {
			ModelAndView modelAndView = new ModelAndView("listar/cadastroescola");
			modelAndView.addObject("escolas", escolaRepository.findEscolaByName("nomepesquisa"));
			modelAndView.addObject("escolaobj", new Escola());
			return modelAndView;
			
		}
		
}
*/
