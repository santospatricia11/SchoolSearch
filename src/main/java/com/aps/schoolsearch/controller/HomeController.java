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
/*	
	@RequestMapping("/listar-escola")
	public String listarEscola(){
		return "listar-escola";
	}
	*/
}
