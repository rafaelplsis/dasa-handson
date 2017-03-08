package com.dasa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dasa.domain.DadoPopulacional;

@Transactional
public interface DadosPopulacionaisRepository extends CrudRepository<DadoPopulacional, Long> {

    DadoPopulacional findByAno(final String ano);

    @Query("select count(*) from DadoPopulacional dp where ano <= :ano")
	int countDadosAnteriores(@Param("ano") String ano);

    @Query("select count(*) from DadoPopulacional dp where ano >= :ano")
	int countDadosFuturo(@Param("ano") String ano);

	List<DadoPopulacional> findByOrderByAnoAsc();

}
