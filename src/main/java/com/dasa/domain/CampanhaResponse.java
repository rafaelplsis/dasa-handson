package com.dasa.domain;

import java.util.List;

import com.dasa.enumerator.TipoCampanha;

import lombok.Data;

@Data
public class CampanhaResponse {

	private final String ano;
	private final TipoCampanha tipoCampanha;
	private final List<Usuario> participantes;
	
	public CampanhaResponse(Campanha c) {
		this.ano = c.getAno();
		this.tipoCampanha = c.getTipoCampanha();
		this.participantes = c.getUsuarios();
	}

}
