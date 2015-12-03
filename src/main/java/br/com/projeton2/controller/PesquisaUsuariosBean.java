package br.com.projeton2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projeton2.model.Usuario;
import br.com.projeton2.repository.Usuarios;
import br.com.projeton2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private List<Usuario> usuariosConsultados;

	private Usuario usuarioSelecionado;

	public void pesquisar() {
		usuariosConsultados = usuarios.getUsuarios();
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuariosConsultados() {
		return usuariosConsultados;
	}

	public void setUsuariosConsultados(List<Usuario> usuariosConsultados) {
		this.usuariosConsultados = usuariosConsultados;
	}
	
	public void excluir() {
		usuarios.remover(usuarioSelecionado);
	
		FacesUtil.addInfoMessage("Usuario excluído com sucesso.");
	}

}