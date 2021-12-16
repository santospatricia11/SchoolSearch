package com.aps.schoolsearch.model.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aps.schoolsearch.model.EnderecoUsuario;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.UsuarioDto;
import com.aps.schoolsearch.model.dto.UsuarioPostDto;

@Component
public class MapperUsuarioDto {
	@Autowired
	private MapperEnderecoDto mapeador;
	
	public UsuarioDto toDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto.setCpf(usuario.getCpf());
		usuarioDto.setDataNascimento(usuario.getDataNascimento());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setEndereco(mapeador.toDto(usuario.getEndereco()));
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setPne(usuario.getPne());
		usuarioDto.setTelefone(usuario.getTelefone());
		usuarioDto.setSexo(usuario.getSexo());
		
		return usuarioDto;
	}
	
	public Usuario toUsuario(UsuarioDto usuarioDto) {	
		Usuario usuario = new Usuario();
		usuario.setEndereco(new EnderecoUsuario());
		
		return toUsuario(usuarioDto, usuario);
	}
	
	public Usuario toUsuario(UsuarioDto usuarioDto, Usuario usuario) {
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setDataNascimento(usuarioDto.getDataNascimento());
		usuario.setEmail(usuarioDto.getEmail());
		
		usuario.setEndereco(
				mapeador.toEndereco(usuarioDto.getEndereco(),
						usuario.getEndereco()));
		
		
		usuario.getEndereco().setUsuario(usuario);
		
		usuario.setNome(usuarioDto.getNome());
		usuario.setPne(usuarioDto.getPne());
		if(usuarioDto instanceof UsuarioPostDto) {
			UsuarioPostDto postDto = (UsuarioPostDto) usuarioDto;
			usuario.setSenha(postDto.getSenha());
		}
		usuario.setTelefone(usuarioDto.getTelefone());
		usuario.setSexo(usuarioDto.getSexo());
		return usuario;
	}
}
