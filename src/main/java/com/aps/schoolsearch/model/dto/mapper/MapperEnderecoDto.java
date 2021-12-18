package com.aps.schoolsearch.model.dto.mapper;

import org.springframework.stereotype.Component;

import com.aps.schoolsearch.model.Endereco;
import com.aps.schoolsearch.model.EnderecoUsuario;
import com.aps.schoolsearch.model.dto.EnderecoDto;

@Component
public class MapperEnderecoDto {
	public EnderecoDto toDto(Endereco endereco) {
		EnderecoDto enderecoDto = new EnderecoDto();
		
		enderecoDto.setLogradouro(endereco.getLogradouro());
		enderecoDto.setNumero(endereco.getNumero());
		enderecoDto.setBairro(endereco.getBairro());
		enderecoDto.setCidade(endereco.getCidade());
		enderecoDto.setEstado(endereco.getEstado());
		
		return enderecoDto;
	}

	public Endereco toEndereco(EnderecoDto enderecoDto, Endereco endereco) {
		
		endereco.setLogradouro(
				enderecoDto.getLogradouro());
		endereco.setNumero(enderecoDto.getNumero());
		endereco.setBairro(enderecoDto.getBairro());
		endereco.setCidade(enderecoDto.getCidade());
		endereco.setEstado(enderecoDto.getEstado());
		
		return endereco;
	}
}
