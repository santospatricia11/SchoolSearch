package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.repository.EscolaRepository;

@Controller
public class EscolaController {
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@RequestMapping("/escola/{id}")
	public String escolaPerfil(@PathVariable("id") Long id, Model model) {
		Escola escola = escolaRepository.findById(id).get();
		model.addAttribute("escola", escola);
		
		return "escola-perfil";
	}
}
