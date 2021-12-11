package com.aps.schoolsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.aps.schoolsearch.repository.EscolaRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private EscolaRepository escolaRepository;

}
