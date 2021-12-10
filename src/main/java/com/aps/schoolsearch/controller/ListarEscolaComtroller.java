package com.aps.schoolsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ListarEscolaComtroller {
	@RequestMapping("/listar-escola")
	public String listarEscola(){
		return "listar-escola";
	}

}
