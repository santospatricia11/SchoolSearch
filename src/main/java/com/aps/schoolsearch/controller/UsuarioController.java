package com.aps.schoolsearch.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.repository.UsuarioRepository;
import com.aps.schoolsearch.service.UsuarioDetails;

@Controller
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UsuarioRepository usuarioRepository;	
	
	@RequestMapping("/perfil")
	public String teste(Model model, Principal principal) {
		Usuario usuario = usuarioRepository.findByEmail(principal.getName());
		model.addAttribute("usuario", usuario);
		
		return "perfil";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
