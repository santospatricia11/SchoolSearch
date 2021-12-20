package com.aps.schoolsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/sobre")
	public String sobre(){
		return "sobre";
	}
	
	@RequestMapping("/preco")
	public String preco(){
		return "preco";
	}
	
	@RequestMapping("/contato")
	public String contato(){
		return "contato";
	}
	
	@RequestMapping("/boleto")
	public String boleto(){
		return "boleto";
	}
	
	@RequestMapping("/cartao-credito")
	public String cartao(){
		return "cartao-credito";
	}
/*	
	@RequestMapping("/listar-escola")
	public String listarEscola(){
		return "listar-escola";
	}
	*/
}
