/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.controle;

import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Veterinario;
import br.ifsul.edu.dao.VeterinarioDAO;
import br.ifsul.edu.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Azzorf
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    @EJB
    private VeterinarioDAO<Veterinario> dao;
    private Veterinario usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }

    public String efetuarLogin() {
        if (dao.login(usuario, senha)) {
            usuarioLogado = dao.localizarUsuarioPorNome(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            AcessoUsuario acesso = new AcessoUsuario(request.getRemoteAddr());
            usuarioLogado.adicionarAcesso(acesso);
            try {
                dao.merge(usuarioLogado);
            } catch (Exception e) {
                Util.msgErro("Erro ao salvar o acesso do usuário: " + e.getMessage());
                return "/index";
            }
            Util.msgInformacao("Usuario logado com sucesso!");
            return "/index";
        } else {
            Util.msgErro("Usuário ou senha inválidos!");
            return "/login";
        }
    }

    public VeterinarioDAO<Veterinario> getDao() {
        return dao;
    }

    public void setDao(VeterinarioDAO<Veterinario> dao) {
        this.dao = dao;
    }

    public Veterinario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Veterinario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}
