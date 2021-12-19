package com.aps.schoolsearch.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.UsuarioDto;
import com.aps.schoolsearch.model.dto.UsuarioPostDto;
import com.aps.schoolsearch.model.dto.mapper.MapperUsuarioDto;
import com.aps.schoolsearch.repository.EnderecoRepository;
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
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	private boolean telefoneExiste(String telefone) {
		return usuarioRepository.findByTelefone(telefone) != null;
	}

	private boolean cpfExiste(String cpf) {
		return usuarioRepository.findByCpf(cpf) != null;
	}
	
	private boolean emailExiste(String email) {
		return usuarioRepository.findByEmail(email) != null;
	}
	private Set<Exception> isUsuarioNoSistema(UsuarioDto usuarioDto) {
		
		Set<Exception> excecoes = new HashSet<>();
		if(cpfExiste(usuarioDto.getCpf())) {
			excecoes.add (new CpfExistsException(
					String.format(
							"O CPF %s já foi cadastrado no sistema."
							, usuarioDto.getCpf()
					)
				));
		}
		if(emailExiste(usuarioDto.getEmail())) {
			excecoes.add(new EmailExisteException());
		}
		if(telefoneExiste(usuarioDto.getTelefone())) {
			excecoes.add(new TelefoneExisteException());
		}
		
		return excecoes;
	}
	
	
	public Set<Exception> registrarNovoUsuario(UsuarioPostDto usuarioDto) {
		String role = "USER";
		
		Set<Exception> excecoes = new HashSet<>();
		if(usuarioRepository.findAll().isEmpty()) {
			role = "ADMIN";
		} else {
			excecoes = isUsuarioNoSistema(usuarioDto);
		}
		
		if(excecoes.isEmpty()) {
			Usuario usuario = mapeadorUsuario.toUsuario(usuarioDto);
			
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			
			usuario.getRoles().add(roleRepository.findByRole(role));
			
			if("ADMIN".equals(role)) {
				usuario
					.getRoles()
						.add(roleRepository.findByRole("USER"));
			}
			usuarioRepository.save(usuario);
		}

		return excecoes;
	}
	
	public void removerUsuario(String username) {
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		usuarioRepository.delete(usuario);
	}
	
	public Set<Exception> editarUsuario(UsuarioDto usuarioDto, String username) {
		
		Set<Exception> excecoes = isUsuarioNoSistema(usuarioDto);
		
		Set<Exception> reais = new HashSet<>();
		
		if(excecoes.isEmpty()) {
			saveUsuario(usuarioDto, username);
		} else {
			for(Exception excecao : excecoes) {
				Usuario erro;
				if(excecao instanceof CpfExistsException) {
					erro = usuarioRepository.findByCpf(usuarioDto.getCpf());
				} else if(excecao instanceof EmailExisteException) {
					erro = usuarioRepository.findByEmail(usuarioDto.getEmail());
				} else {
					erro = usuarioRepository.findByTelefone(usuarioDto.getTelefone());
				}
				if(!username.equals(erro.getEmail())) {
					reais.add(excecao);
				}
			}
		}
		return reais;
	}
	
	private void saveUsuario(UsuarioDto usuarioDto, String username) {
		Usuario usuario = mapeadorUsuario.toUsuario(usuarioDto, usuarioRepository.findByEmail(username));
		
		usuarioRepository.save(usuario);
	}

}
