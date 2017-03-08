package com.dasa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.Participacao;
import com.dasa.domain.Usuario;
import com.dasa.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Usuario salvar(@RequestBody Usuario usuario){
		return usuarioService.salvar(usuario);
	}
	
	@RequestMapping(path="participacao", method=RequestMethod.POST)
	public Participacao notificarParticipacao(@RequestBody Participacao participacao){
		return usuarioService.salvar(participacao);
	}
}
