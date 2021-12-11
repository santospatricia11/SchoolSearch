package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aps.schoolsearch.repository.UsuarioRepository;
@Controller

public class ListarEscolaComtroller {
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

}
