package com.aps.schoolsearch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.model.Endereco;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.assinaturas.Anual;
import com.aps.schoolsearch.model.assinaturas.AssinaturaEscola;
import com.aps.schoolsearch.model.assinaturas.Mensal;
import com.aps.schoolsearch.repository.AssinaturaEscolaRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
@RequestMapping("/cadastro-assinatura-escola")

public class CadastroAssinaturaEscolaMensalController {
	@Value("${spring.application.name}")
	private String appName;

	private static final String CADASTRO_ASSINATURA_ESCOLA_MENSAL = "cadastrar-assinatura-escola";

	@Autowired(required=false)
	private AssinaturaEscolaRepository assinaturaEscolaRepository;

	@ModelAttribute
	private void setModel(Model model) {
		model.addAttribute("app_name", appName);
		model.addAttribute("pagina", CADASTRO_ASSINATURA_ESCOLA_MENSAL);
		model.addAttribute("form_name", "Cadastro de Usuário no" + appName);

		AssinaturaEscola assinaturaMensal = new Mensal();


		/*
		 * model.addAttribute("endereco", endereco); model.addAttribute("usuario",
		 * usuario);
		 */
	}

	@RequestMapping
	@GetMapping
	public String cadastroUsuario(Model model) {
		return CADASTRO_ASSINATURA_ESCOLA_MENSAL;
	}

	/*
	 * @PostMapping("/processar") public String processForm(@Valid Usuario usuario,
	 * BindingResult result) { if (result.hasErrors()) { return
	 * CADASTRO_ASSINATURA_ESCOLA; } usuarioRepository.save(usuario); return
	 * "redirect:/"; }
	 */
}