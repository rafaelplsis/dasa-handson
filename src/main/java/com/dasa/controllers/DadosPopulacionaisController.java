package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.service.DadosPopulacionaisService;

@RestController
@RequestMapping("/dados-populacionais")
public class DadosPopulacionaisController {
	
	
	private DadosPopulacionaisService service;

	@Autowired
	public DadosPopulacionaisController(DadosPopulacionaisService service) {
		this.service = service;

	}
	
	@RequestMapping(path="/{ano}", method=RequestMethod.GET)
	public EstatisticaAnoResponse pesquisarPorAno(@PathVariable("ano") String ano){
		return new EstatisticaAnoResponse(service.obterPopulacaoPorAno(Optional.of(ano)));
	}

}
