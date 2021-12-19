package com.aps.schoolsearch.controller;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.UsuarioDto;
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
	
	private static final String PERFIL = "/perfil";
	

	@RequestMapping
	public String perfil(Model model, Principal principal, HttpSession session) {
		Usuario usuario = usuarioRepository.findByEmail(principal.getName());
		if(usuario == null) {
			return invalidarSessao(session);
		}
		model.addAttribute("usuario", mapeadorUsuario.toDto(usuario));
		
		return "perfil";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		return invalidarSessao(session);
	}
	private String invalidarSessao(HttpSession session){
		session.invalidate();
	    return "redirect:/login?logout";
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
		
		Set<Exception> excecoes = usuarioService.editarUsuario(usuario, principal.getName());
		if(!excecoes.isEmpty()) {
			for(Exception exception: excecoes) {
				if(exception instanceof CpfExistsException) {
					result.addError(new FieldError("cpf","cpf", exception.getMessage()));
				} else if (exception instanceof  EmailExisteException) {
					result.addError(new FieldError("email", "email", exception.getMessage()));
				} else if (exception instanceof TelefoneExisteException) {
					result.addError(new FieldError("telefone", "telefone", exception.getMessage()));
				}
			}
			return PERFIL;
		}
			

		
		return "redirect:/perfil";
	}
}
