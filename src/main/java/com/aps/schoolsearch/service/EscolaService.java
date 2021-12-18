package com.aps.schoolsearch.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aps.schoolsearch.exception.CnpjExistsException;
import com.aps.schoolsearch.exception.EmailExisteException;
import com.aps.schoolsearch.exception.TelefoneExisteException;
import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.Usuario;
import com.aps.schoolsearch.model.dto.EscolaPostDto;
import com.aps.schoolsearch.model.dto.mapper.MapperEscolaDto;
import com.aps.schoolsearch.repository.EscolaRepository;
import com.aps.schoolsearch.repository.UsuarioRepository;

@Service
@Transactional
public class EscolaService {
	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private MapperEscolaDto mapeadorEscola;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private boolean telefoneExiste(String telefone) {
		return escolaRepository.findByTelefone(telefone) != null;
	}

	private boolean cnpjExiste(String cnpj) {
		return escolaRepository.findByCnpj(cnpj) != null;
	}
	
	private boolean emailExiste(String email) {
		return escolaRepository.findByEmail(email) != null;
	}
	
	public void registrarEscola(EscolaPostDto escolaDto, String username) 
			throws 
				CnpjExistsException, 
				EmailExisteException, 
				TelefoneExisteException {
		isEscolaNoSistema(escolaDto);
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		Escola escola = mapeadorEscola.toEscola(escolaDto);
		escola.getEndereco().setEscola(escola);
		
		usuario.setEscola(escola);
		escola.setAdministrador(usuario);
		
		escolaRepository.save(escola);
		
		
	}

	private void isEscolaNoSistema(EscolaPostDto escolaDto) 
			throws 
				CnpjExistsException,
				EmailExisteException, 
				TelefoneExisteException{
		if(cnpjExiste(escolaDto.getCnpj())) {
			throw new CnpjExistsException(
					String.format(
							"O CPF %s já foi cadastrado no sistema."
							, escolaDto.getCnpj()
					)
				);
		}
		if(emailExiste(escolaDto.getEmail())) {
			throw new EmailExisteException();
		}
		if(telefoneExiste(escolaDto.getTelefone())) {
			throw new TelefoneExisteException();
		}
		
	}
	
}
