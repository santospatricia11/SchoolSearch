package com.aps.schoolsearch.model.dto.mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aps.schoolsearch.model.EnderecoEscola;
import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Idioma;
import com.aps.schoolsearch.model.dto.EscolaDto;
import com.aps.schoolsearch.model.dto.EscolaPostDto;
import com.aps.schoolsearch.service.IdiomaService;

@Component
public class MapperEscolaDto {
	@Autowired
	private MapperEnderecoDto mapeadorEndereco;
	
	@Autowired
	private IdiomaService idiomaService;
	
	public EscolaDto toDto(Escola escola) {
		EscolaDto escolaDto = new EscolaDto();
		
		escolaDto.setId(escola.getId());
		escolaDto.setCnpj(escola.getCnpj());
		escolaDto.setEmail(escola.getEmail());
		escolaDto.setEndereco(mapeadorEndereco.toDto(escola.getEndereco()));
		escolaDto.setNome(escola.getNome());
		escolaDto.setTelefone(escola.getTelefone());
		escolaDto.setMensalidade(escola.getMensalidade());
		escolaDto.setLinguas(escola.getLinguas());
		
		escolaDto.setClassificacaoEnsino(escola.getClassificacaoEnsino());
		escolaDto.setNivelEnsino(escola.getNivelEnsino());
		escolaDto.setMetodoEnsino(escola.getMetodoEnsino());
		
		return escolaDto;
	}
	
	public Escola toEscola(EscolaDto escolaDto) {	
		Escola escola = new Escola();
		
		escola.setEndereco(new EnderecoEscola());
		
		return toEscola(escolaDto, escola);
	}
	
	public Escola toEscola(EscolaDto escolaDto, Escola escola) {
		
		escola.setCnpj(escolaDto.getCnpj());
		escola.setEmail(escolaDto.getEmail());
		EnderecoEscola endereco = (EnderecoEscola) mapeadorEndereco.toEndereco(escolaDto.getEndereco(), escola.getEndereco());
		escola.setEndereco(endereco);
		escola.setNome(escolaDto.getNome());
		escola.setTelefone(escolaDto.getTelefone());
		escola.setMensalidade(escolaDto.getMensalidade());
		if(escolaDto instanceof EscolaPostDto) {
			EscolaPostDto escolaPostDto = (EscolaPostDto) escolaDto;
			
			String idiomasString = escolaPostDto.getIdiomas();
			Set<String> idiomasSet = Arrays.stream(idiomasString.split(",")).map(String::trim).collect(Collectors.toSet());
			
			Set<Idioma> idiomas = new HashSet<>();
			idiomas.add(idiomaService.registrarIdioma("Português Brasileiro"));
			for(String idioma: idiomasSet) {
				Idioma cadastrado = idiomaService.registrarIdioma(idioma);
				idiomas.add(cadastrado);
			}
			escola.setLinguas(idiomas);
		} else {
			escola.setLinguas(escolaDto.getLinguas());
		}
		
		escola.setClassificacaoEnsino(escolaDto.getClassificacaoEnsino());
		escola.setNivelEnsino(escolaDto.getNivelEnsino());
		escola.setMetodoEnsino(escolaDto.getMetodoEnsino());
		
		return escola;
	}
}
