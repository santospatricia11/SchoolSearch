package com.aps.schoolsearch.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.Endereco;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.UsuarioPostDto;
import com.aps.schoolsearch.model.dto.mapper.MapperUsuarioDto;
import com.aps.schoolsearch.repository.RoleRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MapperUsuarioDto mapeadorUsuario;
	
	public Usuario registrarNovoUsuario(UsuarioPostDto usuarioDto) 
			throws 
				CpfExistsException, 
				EmailExisteException,
				TelefoneExisteException {
		String role = "USER";
		if(usuarioRepository.findAll().isEmpty()) {
			role = "ADMIN";
		} else {
			if(cpfExiste(usuarioDto.getCpf())) {
				throw new CpfExistsException(
						String.format(
								"O CPF %s já foi cadastrado no sistema."
								, usuarioDto.getCpf()
						)
					);
			}
			if(emailExiste(usuarioDto.getEmail())) {
				throw new EmailExisteException();
			}
			if(telefoneExiste(usuarioDto.getTelefone())) {
				throw new TelefoneExisteException();
			}
		}
		Usuario usuario = mapeadorUsuario.toUsuario(usuarioDto);
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		
		usuario.setRoles(
				Stream.of(
					roleRepository.findByRole(role))
				.collect(Collectors.toSet()));
		return usuarioRepository.save(usuario);
	}
	
	private boolean telefoneExiste(String telefone) {
		return usuarioRepository.findByTelefone(telefone) != null;
	}

	private boolean cpfExiste(String cpf) {
		return usuarioRepository.findByCpf(cpf) != null;
	}
	
	private boolean emailExiste(String email) {
		return usuarioRepository.findByEmail(email) != null;
	}
}
