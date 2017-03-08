package com.dasa.service;

import java.util.List;
import java.util.Optional;

import com.dasa.domain.Campanha;

public interface CampanhaService {
	public List<Campanha> listarCampanhaPorAno(Optional<String> ano);
}
