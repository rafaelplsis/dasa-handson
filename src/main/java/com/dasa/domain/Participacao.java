package com.dasa.domain;

import com.dasa.enumerator.Sexo;
import com.dasa.enumerator.TipoCampanha;

import lombok.Data;

@Data
public class Participacao {

	private String nome;
	private Sexo	sexo;
	private String ano;
	private TipoCampanha campanha;
	
	public Participacao() {
	}
	
	public Participacao(UsuarioCampanha usuarioCampanha) {
	
		this.nome = usuarioCampanha.getUsuario().getNome();
		this.sexo = usuarioCampanha.getUsuario().getSexo();
		this.ano = usuarioCampanha.getCampanha().getAno();
		this.campanha = usuarioCampanha.getCampanha().getTipoCampanha();
	}

	public String getNome() {
		return nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getAno() {
		return ano;
	}

	public TipoCampanha getCampanha() {
		return campanha;
	}
}
