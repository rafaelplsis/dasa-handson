package com.dasa.service;

import com.dasa.domain.Participacao;
import com.dasa.domain.Usuario;

public interface UsuarioService {

	
	public Usuario salvar(Usuario usuario);
	public Usuario buscarPorNome(String nomeUsuario);
	public Usuario buscarPorId(Long id);
	public Participacao salvar(Participacao participacao);
	
}
