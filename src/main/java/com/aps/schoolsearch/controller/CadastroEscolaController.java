package com.aps.schoolsearch.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;
import com.aps.schoolsearch.model.dto.EnderecoDto;
import com.aps.schoolsearch.model.dto.EscolaPostDto;

@Controller
@RequestMapping("/cadastrar-escola")
public class CadastroEscolaController {
	
	private final String CADASTRO_ESCOLA = "cadastrar-escola";
	
	@ModelAttribute
	public void setModel(Model model) {
		model.addAttribute("classificacoesEnsino", ClassificacaoEnsino.values());
		model.addAttribute("metodosEnsino", MetodoEnsino.values());
		model.addAttribute("niveisEnsino", NivelEnsino.values());
		
		EscolaPostDto escola = new EscolaPostDto();
		escola.setEndereco(new EnderecoDto());
		
		model.addAttribute("escola", new EscolaPostDto());
		
	}
	
	@RequestMapping
	@GetMapping
	public String cadastrarEscola(Model model, Principal principal) {
		return CADASTRO_ESCOLA;
	}
}
