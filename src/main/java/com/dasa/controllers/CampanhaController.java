package com.dasa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.Campanha;
import com.dasa.domain.CampanhaResponse;
import com.dasa.domain.CampanhaResumoResponse;
import com.dasa.service.CampanhaService;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

	private CampanhaService campanhaService;
	
	@Autowired
	public CampanhaController(CampanhaService campanhaService) {
		this.campanhaService = campanhaService;
	}
	
	@RequestMapping("/{ano}")
	public List<CampanhaResponse> campanhasPorAno(@PathVariable("ano") String ano){
		return converter(campanhaService.listarCampanhaPorAno(Optional.of(ano)));
	}
	
	@RequestMapping("/{ano}/resumo")
	public List<CampanhaResumoResponse> campanhasResumidoPorAno(@PathVariable("ano") String ano){
		return converterResumo(campanhaService.listarCampanhaPorAno(Optional.of(ano)));
	}	

	private List<CampanhaResponse> converter(List<Campanha> listarCampanhaPorAno) {
		List<CampanhaResponse> retorno = new ArrayList<>();
		
		listarCampanhaPorAno.forEach(c -> {
			retorno.add(new CampanhaResponse(c));
		});
		
		return retorno;
	}
	
	private List<CampanhaResumoResponse> converterResumo(List<Campanha> listarCampanhaPorAno) {
		List<CampanhaResumoResponse> retorno = new ArrayList<>();
		
		listarCampanhaPorAno.forEach(c -> {
			retorno.add(new CampanhaResumoResponse(c));
		});
		
		return retorno;
	}	
}
