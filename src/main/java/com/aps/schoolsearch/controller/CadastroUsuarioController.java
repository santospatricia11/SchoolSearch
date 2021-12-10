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

import com.aps.schoolsearch.model.Endereco;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.EnderecoDto;
import com.aps.schoolsearch.model.dto.UsuarioDto;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
@RequestMapping("/cadastrar-usuario")
public class CadastroUsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	private static final String CADASTRO_USUARIO = "cadastrar-usuario";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@ModelAttribute
	private void setModel(Model model) {
		model.addAttribute("app_name", appName);
		model.addAttribute("pagina", CADASTRO_USUARIO);
		model.addAttribute("form_name", "Cadastro de Usuário no"+appName);
		
		UsuarioDto usuario = new UsuarioDto();
		EnderecoDto endereco = new EnderecoDto();
		
		usuario.setEndereco(endereco);
		
		model.addAttribute("endereco", endereco);
		model.addAttribute("usuario", usuario);
	}
	
	@RequestMapping
	@GetMapping
	public String cadastroUsuario(Model model) {
		return CADASTRO_USUARIO;
	}
	
	@PostMapping("/processar")
	public String processForm(@ModelAttribute("usuario") @Valid UsuarioDto usuario, BindingResult result) {
		if(result.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		System.out.println(usuario.getDataNascimento());
		Usuario novo = new Usuario(usuario);
		novo.setEndereco(new Endereco(usuario.getEndereco()));
		novo.getEndereco().setUsuario(novo);
		
		usuarioRepository.save(novo);
		return "redirect:/";
	}
}
