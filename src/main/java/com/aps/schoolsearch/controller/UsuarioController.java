package com.aps.schoolsearch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private void setModel(Model model) {
		model.addAttribute("app_name", appName);
	}
	
	@GetMapping("/add")
	public String empregoForm(Model model) {
		model.addAttribute("form_name", "Formulário de Empregos em Spring Boot");
		model.addAttribute("usuario", new Usuario());
		return "usuario-form";
	}
	
	@PostMapping("/process")
	public String processForm(@Valid Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "usuario-form";
		}
		usuarioRepository.save(usuario);
		return "redirect:/";
	}
}
