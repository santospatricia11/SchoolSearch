package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;
import com.aps.schoolsearch.repository.EscolaRepository;
@Controller

public class ListarEscolaController {
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@ModelAttribute
	public void modelAttributes(Model model) {
		model.addAttribute("escolas", escolaRepository.findAll());
		model.addAttribute("classificacaoEnsino", ClassificacaoEnsino.values());
		model.addAttribute("nivelEnsino", NivelEnsino.values());
		model.addAttribute("metodoEnsino", MetodoEnsino.values());
		
		model.addAttribute("escolas", escolaRepository.findAll());
	}
	
	@RequestMapping("/pesquisa")
	public String pesquisa(Model model) {
		return "listar-escola";
	}
}
