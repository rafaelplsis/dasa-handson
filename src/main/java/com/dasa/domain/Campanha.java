package com.dasa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dasa.enumerator.TipoCampanha;

import lombok.Data;

@Data
@Entity
@Table(name = "dados_campanha")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String ano;
    
    @Enumerated(EnumType.STRING)
    private TipoCampanha tipoCampanha;

    @Transient
    private List<Usuario> usuarios;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public TipoCampanha getTipoCampanha() {
		return tipoCampanha;
	}

	public void setTipoCampanha(TipoCampanha tipoCampanha) {
		this.tipoCampanha = tipoCampanha;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
