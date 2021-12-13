package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.repository.EscolaRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;
@Controller

public class ListarEscolaComtroller {
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	

}
