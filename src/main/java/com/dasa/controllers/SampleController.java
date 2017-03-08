package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.service.DadosPopulacionaisService;

@RestController
public class SampleController {

	@Autowired
	DadosPopulacionaisService service;
	
	@RequestMapping(path="/hello", method=RequestMethod.GET)
	public String helloWorld(){
		return "Hello =)";
	}
	
	@RequestMapping(path="/2010", method=RequestMethod.GET)
	public EstatisticaAnoResponse get2010data(){
		
		DadoPopulacional pop = service.obterPopulacaoPorAno(Optional.of("2010"));
		EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);
		return stat;
	}
	
}
