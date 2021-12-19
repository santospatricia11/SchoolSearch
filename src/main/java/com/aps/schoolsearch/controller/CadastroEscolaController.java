package com.aps.schoolsearch.controller;

import java.security.Principal;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.exception.CnpjExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;
import com.aps.schoolsearch.model.dto.EnderecoDto;
import com.aps.schoolsearch.model.dto.EscolaPostDto;
import com.aps.schoolsearch.repository.IdiomaRepository;
import com.aps.schoolsearch.service.EscolaService;

@Controller
@RequestMapping("/cadastrar-escola")
public class CadastroEscolaController {
	
	private final String CADASTRO_ESCOLA = "cadastrar-escola";
	
	@Autowired
	private EscolaService escolaService;
	
	@Autowired
	private IdiomaRepository idiomaRepository;
	
	@ModelAttribute
	public void setModel(Model model) {
		model.addAttribute("classificacoesEnsino", ClassificacaoEnsino.values());
		model.addAttribute("metodosEnsino", MetodoEnsino.values());
		model.addAttribute("niveisEnsino", NivelEnsino.values());
		model.addAttribute("idiomasCadastrados", idiomaRepository.findAll());
		
		EscolaPostDto escola = new EscolaPostDto();
		escola.setEndereco(new EnderecoDto());
		
		model.addAttribute("escola", escola);
		
	}
	
	@RequestMapping
	@GetMapping
	public String cadastrarEscola(Model model, Principal principal) {
		return CADASTRO_ESCOLA;
	}
	
	@PostMapping("/processar")
	public String processarCadastro(
			@ModelAttribute("escola") 
				@Valid EscolaPostDto usuario, 
			BindingResult result,
			Principal principal) {
		if(result.hasErrors()) {
			return CADASTRO_ESCOLA;
		}
		
		
		Set<Exception> excecoes = escolaService.registrarEscola(usuario, principal.getName());
		if(!excecoes.isEmpty()) {
			for(Exception excecao : excecoes ) {
				if(excecao instanceof CnpjExistsException) {
					result.addError(new FieldError("cnpj","cnpj", excecao.getMessage()));
				} else if(excecao instanceof EmailExisteException) {
					result.addError(new FieldError("email", "email", excecao.getMessage()));
				}else if(excecao instanceof TelefoneExisteException) {
					result.addError(new FieldError("telefone", "telefone", excecao.getMessage()));
				}
			}

			return CADASTRO_ESCOLA;
		} 
		
		return "redirect:/perfil";
	}
}
