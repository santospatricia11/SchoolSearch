package com.aps.schoolsearch.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.UsuarioDto;
import com.aps.schoolsearch.model.dto.UsuarioPostDto;
import com.aps.schoolsearch.model.dto.mapper.MapperUsuarioDto;
import com.aps.schoolsearch.repository.UsuarioRepository;
import com.aps.schoolsearch.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Autowired
	public MapperUsuarioDto mapeadorUsuario;
	
	@Autowired
	public UsuarioService usuarioService;
	
	

	@RequestMapping
	public String perfil(Model model, Principal principal) {
		Usuario usuario = usuarioRepository.findByEmail(principal.getName());
		
		model.addAttribute("usuario", mapeadorUsuario.toDto(usuario));
		
		return "perfil";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@PostMapping("/delete")
	public String removerUsuario(UsuarioDto usuarioDto, Principal principal) {
		
		usuarioService.removerUsuario(principal.getName());
		
		return "redirect:/login?logout";
	}
	
	@PostMapping("/editar")
	public String editarUsuario(
			@ModelAttribute("usuario") 
				@Valid UsuarioDto usuario
			, BindingResult result, 
			Principal principal) {
		
		
		try {
			usuarioService.editarUsuario(usuario, principal.getName());
		} catch(CpfExistsException exception) {
			result.addError(new FieldError("cpf","cpf", exception.getMessage()));
			return "/perfil";
		} catch(EmailExisteException exception) {
			result.addError(new FieldError("email", "email", exception.getMessage()));
			return "/perfil";
		} catch(TelefoneExisteException exception) {
			result.addError(new FieldError("telefone", "telefone", exception.getMessage()));
			return "/perfil";
		}
		
		return "redirect:/perfil";
	}
}
