package com.dasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dasa.domain.Usuario;
import com.dasa.domain.UsuarioCampanha;

@Repository
public interface UsuarioCampanhaRepository extends CrudRepository<UsuarioCampanha, Long>{

	@Query("select uc.usuario from UsuarioCampanha uc where uc.campanha.id = :id")
	List<Usuario> findUsuarioByCampanha(@Param("id") Long idCampanha);
}
