package br.com.projeton2.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeton2.model.Grupo;
import br.com.projeton2.model.Usuario;
import br.com.projeton2.repository.Grupos;
import br.com.projeton2.service.CadastroUsuarioService;
import br.com.projeton2.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Inject
	private Grupos grupos;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;

	private List<Grupo> grupoprincipais;

	public LoginBean() {
		usuario = new Usuario();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			grupoprincipais = grupos.getGrupos();
		}
	}

	private String email;

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);

		facesContext.responseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void salvar() {

		this.usuario = cadastroUsuarioService.salvar(usuario);
		limpar();

		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public List<Grupo> getGrupoprincipais() {
		return grupoprincipais;
	}

	public void setGrupoprincipais(List<Grupo> grupoprincipais) {
		this.grupoprincipais = grupoprincipais;
	}

}