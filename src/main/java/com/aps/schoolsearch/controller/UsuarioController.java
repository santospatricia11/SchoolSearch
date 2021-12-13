package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.repository.EscolaRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UsuarioRepository usuarioRepository;	
	
	@RequestMapping("/teste")
	public String teste(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		
		return "teste";
	}
}
