package br.com.projeton2.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.projeton2.model.Usuario;
import br.com.projeton2.repository.Usuarios;
import br.com.projeton2.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		return usuarios.guardar(usuario);
	}

}
