package com.dasa.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.dasa.domain.DadoPopulacional;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.utils.ProjecaoGeometrica;

public class DadosPopulacionaisServiceImplTest {

	
	private DadosPopulacionaisRepository fakeRepository;
	private ProjecaoGeometrica projecao;

	@Test(expected=IllegalArgumentException.class)
	public void naoPermitePesquisarPorAnoAnteriorInicioPesquisa(){
		String anoPesquisa = "1999";
		when(fakeRepository.countDadosAnteriores(anoPesquisa))
			.thenReturn(0);
		
		
		DadosPopulacionaisServiceimpl dadosServices = new DadosPopulacionaisServiceimpl(fakeRepository, projecao);
		dadosServices.obterPopulacaoPorAno(Optional.of(anoPesquisa));
		
		verify(fakeRepository).countDadosAnteriores(anoPesquisa);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPermitePesquisaPorAnoFuturo(){
		String anoPesquisa = "2018";
		when(fakeRepository.countDadosAnteriores(anoPesquisa))
		.thenReturn(1);		
		when(fakeRepository.countDadosFuturo(anoPesquisa))
			.thenReturn(0);
		
		
		DadosPopulacionaisServiceimpl dadosServices = new DadosPopulacionaisServiceimpl(fakeRepository, projecao);
		dadosServices.obterPopulacaoPorAno(Optional.of(anoPesquisa));
		verify(fakeRepository).countDadosAnteriores(anoPesquisa);		
		verify(fakeRepository).countDadosFuturo(anoPesquisa);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPermitePesquisarPorAnoSemValor(){
		new DadosPopulacionaisServiceimpl(fakeRepository, projecao).obterPopulacaoPorAno(Optional.empty());
	}
	
	@Test
	public void deveRetornarPorProjecaoParaAnoCorrenteSemPesquisa(){
		String anoPesquisa = "2017";
		List<DadoPopulacional> dadosPopulacionais = listaDadosPopulacionais();
		
		when(fakeRepository.countDadosAnteriores(anoPesquisa))
		.thenReturn(1);		
		when(fakeRepository.countDadosFuturo(anoPesquisa))
			.thenReturn(1);
		when(fakeRepository.findByAno(anoPesquisa)).thenReturn(null);
		
		when(fakeRepository.findByOrderByAnoAsc()).thenReturn(dadosPopulacionais);
		
		DadoPopulacional dadoTeste = projecao.aplicaoEstimativa(dadosPopulacionais.get(0), dadosPopulacionais.get(4));
		
		DadosPopulacionaisServiceimpl dadosServices = new DadosPopulacionaisServiceimpl(fakeRepository, projecao);
		DadoPopulacional dadosPopulacional = dadosServices.obterPopulacaoPorAno(Optional.of(anoPesquisa));
		
		assertThat(dadosPopulacional.getAno(), equalTo(dadoTeste.getAno()));
		assertThat(dadosPopulacional.getPopulacaoTotal(), equalTo(dadoTeste.getPopulacaoTotal()));
		assertThat(dadosPopulacional.getTotalHomens(), equalTo(dadoTeste.getTotalHomens()));
		assertThat(dadosPopulacional.getTotalMulheres(), equalTo(dadoTeste.getTotalMulheres()));		
	}
	
	private List<DadoPopulacional> listaDadosPopulacionais() {
		
		return Arrays.asList(new DadoPopulacional("2000", "173448346", "86169657", "87278689"),
							 new DadoPopulacional("2001", "175885229", "87336165", "88549064"),
							 new DadoPopulacional("2010", "195497797", "96706703", "98791094"),
							 new DadoPopulacional("2013", "201032714", "99336858", "101695856"),
							 new DadoPopulacional("2016", "206081432", "101726102", "104355330")							 
				);
	}

	@Before
	public void inicializar(){
		this.fakeRepository = mock(DadosPopulacionaisRepository.class);
		this.projecao = new ProjecaoGeometrica();
	}
}
