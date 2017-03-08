package com.dasa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dasa.domain.Campanha;
import com.dasa.enumerator.TipoCampanha;

@Repository
public interface CampanhaRepository extends CrudRepository<Campanha, Long>{

	Campanha findByAnoAndTipoCampanha(String ano, TipoCampanha campanha);

	List<Campanha> findByAno(String a);

}
