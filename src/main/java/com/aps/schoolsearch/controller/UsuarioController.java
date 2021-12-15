package com.aps.schoolsearch.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.mapper.MapperUsuarioDto;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Autowired
	public MapperUsuarioDto mapeadorUsuario;
	
	@RequestMapping("/perfil")
	public String teste(Model model, Principal principal) {
		Usuario usuario = usuarioRepository.findByEmail(principal.getName());
		
		model.addAttribute("usuario", mapeadorUsuario.toDto(usuario));
		
		return "perfil";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
