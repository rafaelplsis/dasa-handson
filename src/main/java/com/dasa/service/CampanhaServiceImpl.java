package com.dasa.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dasa.domain.Campanha;
import com.dasa.domain.Usuario;
import com.dasa.repository.CampanhaRepository;
import com.dasa.repository.UsuarioCampanhaRepository;

@Service
public class CampanhaServiceImpl implements CampanhaService{

	private UsuarioCampanhaRepository usuarioCampanhaRepository;
	private CampanhaRepository campanhaRepository;

	public CampanhaServiceImpl(CampanhaRepository campanhaRepository, UsuarioCampanhaRepository usuarioCampanhaRepository) {
		this.campanhaRepository = campanhaRepository;
		this.usuarioCampanhaRepository = usuarioCampanhaRepository;
	}
	
	
	
	public List<Campanha> listarCampanhaPorAno(Optional<String> ano){
		List<Campanha> retorno = Collections.emptyList();
		if(ano.isPresent())
			retorno = preencherDadosParticipantes(campanhaRepository.findByAno(ano.get()));
		
		return retorno;
	}



	private List<Campanha> preencherDadosParticipantes(List<Campanha> campanhas) {
		
			campanhas.forEach(c -> {
				List<Usuario> usuarios = usuarioCampanhaRepository.findUsuarioByCampanha(c.getId());
				if(usuarios != null)
					c.setUsuarios(usuarios);
			});
			
			return campanhas;
	}
}
