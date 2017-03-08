package com.dasa.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.utils.ProjecaoGeometrica;

@Service
public class DadosPopulacionaisServiceimpl implements DadosPopulacionaisService {

    
    private DadosPopulacionaisRepository dadosPopulacionaisRepository;
	private ProjecaoGeometrica projecao;

    @Autowired
    public DadosPopulacionaisServiceimpl(DadosPopulacionaisRepository dadosPopulacionaisRepository, ProjecaoGeometrica projecao){
		this.dadosPopulacionaisRepository = dadosPopulacionaisRepository;
		this.projecao = projecao;
    }
    
    @Override
    public DadoPopulacional obterPopulacaoPorAno(final Optional<String> ano) {

        final String anoCenso = validarAno(ano);
        
        DadoPopulacional resultado = dadosPopulacionaisRepository.findByAno(anoCenso);

        if(resultado == null){
        	List<DadoPopulacional> lista = dadosPopulacionaisRepository.findByOrderByAnoAsc();
        	
        	DadoPopulacional inicio = lista.get(0);
        	DadoPopulacional fim = lista.get(lista.size() -1);
        	
        	resultado = projecao.aplicaoEstimativa(inicio, fim);
        }
        
        return resultado;
    }

    /**
     * @author Rafael Pereira
     * @return {@link String} ano extra�do do {@link Optional}
     * @throws IllegalArgumentException Se o ano informado estiver vazio,nulo ou se for inferior 
     * ao menor ano da base de dados
     */
	private String validarAno(Optional<String> ano) {
		String anoPesquisa = ano.orElseThrow(() -> new IllegalArgumentException("Parametro Ano é Obrigatório"));
		
		if(dadosPopulacionaisRepository.countDadosAnteriores(anoPesquisa) == 0)
			throw new IllegalArgumentException("Ano informado é inferior ao ano de inicio da pesquisa");
		
		if(Year.parse(anoPesquisa).isAfter(Year.now()))
			throw new IllegalArgumentException("Não é possível informar data futura");
		
		return anoPesquisa;
	}
}
