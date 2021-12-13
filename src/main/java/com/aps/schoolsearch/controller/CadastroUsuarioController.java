package com.aps.schoolsearch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.dto.UsuarioDto;
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
		
		UsuarioDto usuario = new UsuarioDto();
		
		model.addAttribute("usuario", usuario);
	}
	
	@RequestMapping
	@GetMapping
	public String cadastroUsuario(Model model) {
		return CADASTRO_USUARIO;
	}
	
	@PostMapping("/processar")
	public String processForm(@ModelAttribute("usuario") @Valid UsuarioDto usuario, BindingResult result) throws CpfExistsException, EmailExisteException, TelefoneExisteException {
		if(result.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		
		usuarioService.registrarNovoUsuario(usuario);
		
		return "redirect:/";
	}
}
