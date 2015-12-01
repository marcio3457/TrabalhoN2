package br.com.projetodw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.projetodw.model.Usuario;
import br.com.projetodw.repository.Usuarios;
import br.com.projetodw.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		return usuarios.guardar(usuario);
	}

}
