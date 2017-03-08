package com.dasa.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Year;

import org.springframework.stereotype.Component;

import com.dasa.domain.DadoPopulacional;

@Component
public class ProjecaoGeometrica {

	public DadoPopulacional aplicaoEstimativa(DadoPopulacional inicio, DadoPopulacional fim) {
		String anoProjecao = Year.parse(fim.getAno()).plusYears(1).toString();
		
		double taxaCrescimento = taxaCrescimento(inicio, fim);
		
		BigDecimal totalPopulacional = acrescentarTaxa(fim.getPopulacaoTotal(), taxaCrescimento);
		
		Long homens = acrescentarTaxa(fim.getTotalHomens(), taxaCrescimento);
		Long mulheres = acrescentarTaxa(fim.getTotalMulheres(), taxaCrescimento);	
		
		return new DadoPopulacional(anoProjecao, totalPopulacional, homens, mulheres);
  	
	}

	private BigDecimal acrescentarTaxa(BigDecimal valorAtual, double taxa) {
		
		return valorAtual.add(BigDecimal.valueOf(taxa).multiply(valorAtual)).round(MathContext.DECIMAL64);
	}
	
	private Long acrescentarTaxa(Long valorAtual, double taxa) {
		
		valorAtual += Math.round(valorAtual.doubleValue() * taxa);
		return valorAtual;
	}	
	
	private double taxaCrescimento(DadoPopulacional inicioPeriodo, DadoPopulacional ultimaMedicao) {
		BigInteger anoPrimeiraMedicao = new BigInteger(inicioPeriodo.getAno()); 
		BigInteger anoUltimaMedicao = new BigInteger(ultimaMedicao.getAno());
		BigDecimal intervalo = BigDecimal.valueOf(anoUltimaMedicao.subtract(anoPrimeiraMedicao).longValue());
		
    	BigDecimal totalAnos = BigDecimal.ONE.divide(intervalo,2,RoundingMode.HALF_UP);
    	double resultadoDivisao = ultimaMedicao.getPopulacaoTotal()
    									.divide(inicioPeriodo.getPopulacaoTotal(),5,RoundingMode.HALF_UP)
    									.doubleValue();
    	
		return Math.pow(resultadoDivisao,totalAnos.doubleValue()) -1;
	}	

}
