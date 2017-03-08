package com.dasa.repository;

import org.springframework.data.repository.CrudRepository;

import com.dasa.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findByNome(String nomeUsuario);

}
