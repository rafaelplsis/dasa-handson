package com.dasa.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class EstatisticaAnoResponse {
	private final String ano;
	private final BigDecimal totalPopulacional;
	@Getter(AccessLevel.NONE)
	private final Long totalHomens;
	@Getter(AccessLevel.NONE)
	private final Long totalMulheres;
	
	public EstatisticaAnoResponse(DadoPopulacional pop) {
		this.ano = pop.getAno();
		this.totalPopulacional = pop.getPopulacaoTotal();
		this.totalHomens = pop.getTotalHomens();
		this.totalMulheres = pop.getTotalMulheres();
	}
	@JsonGetter("percentualHomens")
	public BigDecimal getProporcaoHomens(){
		return percentual(totalPopulacional, totalHomens);
	
	}
	
	@JsonGetter("percentualMulheres")	
	public BigDecimal getProporcaoMulheres(){
		return percentual(totalPopulacional, totalMulheres);		
	}
	
	private BigDecimal percentual(BigDecimal total, Long amostra){
		return BigDecimal.valueOf(amostra * 100).divide(total, 2,BigDecimal.ROUND_HALF_UP);
	}
}
