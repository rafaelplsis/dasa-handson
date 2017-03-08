package com.dasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.domain.Campanha;
import com.dasa.domain.Participacao;
import com.dasa.domain.Usuario;
import com.dasa.domain.UsuarioCampanha;
import com.dasa.repository.CampanhaRepository;
import com.dasa.repository.UsuarioCampanhaRepository;
import com.dasa.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	
	private UsuarioRepository usuarioRepository;
	private UsuarioCampanhaRepository usuarioCampanhaRepository;
	private CampanhaRepository campanhaRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioCampanhaRepository usuarioCampanhaRepository, CampanhaRepository campanhaRepository) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioCampanhaRepository = usuarioCampanhaRepository;
		this.campanhaRepository = campanhaRepository;
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarPorNome(String nomeUsuario) {
		return usuarioRepository.findByNome(nomeUsuario);
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public Participacao salvar(Participacao participacao) {
		
		Campanha campanha = campanhaRepository.findByAnoAndTipoCampanha(participacao.getAno(),participacao.getCampanha());
		Usuario usuario  = usuarioRepository.findByNome(participacao.getNome());
		UsuarioCampanha uc = new UsuarioCampanha();
		uc.setCampanha(campanha);
		uc.setUsuario(usuario);
		
		return new Participacao(usuarioCampanhaRepository.save(uc));
	}

}
