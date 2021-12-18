package com.aps.schoolsearch.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aps.schoolsearch.exception.CpfExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.EnderecoUsuario;
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
	
	
	public Usuario registrarNovoUsuario(UsuarioPostDto usuarioDto) 
			throws 
				CpfExistsException, 
				EmailExisteException,
				TelefoneExisteException {
		String role = "USER";
		if(usuarioRepository.findAll().isEmpty()) {
			role = "ADMIN";
		} else {
			isUsuarioNoSistema(usuarioDto);
		}
		
		Usuario usuario = mapeadorUsuario.toUsuario(usuarioDto);
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		
		usuario.getRoles().add(roleRepository.findByRole(role));
		
		if("ADMIN".equals(role)) {
			usuario
				.getRoles()
					.add(roleRepository.findByRole("USER"));
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public void removerUsuario(String username) {
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		usuarioRepository.delete(usuario);
	}
	
	public void editarUsuario(UsuarioDto usuarioDto, String username) 
			throws 
				CpfExistsException, 
				EmailExisteException, 
				TelefoneExisteException {
		try {
			isUsuarioNoSistema(usuarioDto);
		}catch(CpfExistsException exception) {
			// usuario que possui o cpf cadastro
			Usuario cpf = usuarioRepository.findByCpf(usuarioDto.getCpf());
			
			// se o usuario logado tem o mesmo email do usuario com esse cpf
			if(!username.equals(cpf.getEmail())) {
				throw exception;
			}
		} catch(EmailExisteException exception) {
			Usuario email = usuarioRepository.findByEmail(usuarioDto.getEmail());
			if(!username.equals(email.getEmail())) {
				throw exception;
			}
		} catch(TelefoneExisteException exception) {
			Usuario telefone = usuarioRepository.findByTelefone(usuarioDto.getTelefone());
			if(!username.equals(telefone.getEmail())) {
				throw exception;
			}
		}
		
		Usuario usuario = mapeadorUsuario.toUsuario(usuarioDto, usuarioRepository.findByEmail(username));
		
		usuarioRepository.save(usuario);
	}
	
	private void isUsuarioNoSistema(UsuarioDto usuarioDto) 
			throws CpfExistsException, 
			EmailExisteException, 
			TelefoneExisteException{
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

}
