package com.dasa.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.dasa.enumerator.Sexo;
import com.dasa.enumerator.TipoCampanha;

import lombok.Data;

@Data
public class CampanhaResumoResponse {
	private final String ano;
	private TipoCampanha tipoCampanha;
	private final BigInteger percentualHomens;
	private final BigInteger percentualMulheres;
	
	public CampanhaResumoResponse(Campanha campanha) {
		ano = campanha.getAno();
		tipoCampanha = campanha.getTipoCampanha();
		percentualHomens = percentual(campanha.getUsuarios(), Sexo.M);
		percentualMulheres = percentual(campanha.getUsuarios(), Sexo.F);
	}
	
	private BigInteger percentual(List<Usuario> usuarios, Sexo sexo){
		if(usuarios.isEmpty())
			return BigInteger.ZERO;
		
		BigInteger total = BigInteger.valueOf(usuarios.size());
		BigInteger amostra = BigInteger.valueOf(usuarios.stream()
												.filter(u -> u.getSexo().equals(sexo))
												.count() * 100);
		
		return amostra.divide(total);
	}

}
