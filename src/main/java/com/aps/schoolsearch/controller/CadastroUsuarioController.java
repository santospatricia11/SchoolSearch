package com.aps.schoolsearch.controller;

import java.util.Set;

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
import com.aps.schoolsearch.model.dto.EnderecoDto;
import com.aps.schoolsearch.model.dto.UsuarioPostDto;
import com.aps.schoolsearch.repository.UsuarioRepository;
import com.aps.schoolsearch.service.UsuarioService;

@Controller
@RequestMapping("/cadastrar-usuario")
public class CadastroUsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	private static final String CADASTRO_USUARIO = "cadastrar-usuario";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute
	private void setModel(Model model) {
		model.addAttribute("app_name", appName);
		model.addAttribute("pagina", CADASTRO_USUARIO);
		model.addAttribute("form_name", "Cadastro de Usuário no"+appName);
		model.addAttribute("usuarios", usuarioRepository.findAll());
		
		UsuarioPostDto usuario = new UsuarioPostDto();
		usuario.setEndereco(new EnderecoDto());
		
		model.addAttribute("usuario", usuario);
	}
	
	@RequestMapping
	@GetMapping
	public String cadastroUsuario(Model model) {
		return CADASTRO_USUARIO;
	}
	
	@PostMapping("/processar")
	public String processForm(
			@ModelAttribute("usuario") 
				@Valid UsuarioPostDto usuario, 
			BindingResult result) {
		if(result.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		
		Set<Exception> excecoes = usuarioService.registrarNovoUsuario(usuario);
		
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
			return CADASTRO_USUARIO;
		}
		
		return "redirect:/";
	}
}
