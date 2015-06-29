package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Item;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.GenericDAO;
import br.edu.ifsul.util.Util;

import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleCliente")
@ViewScoped
public class ControleCliente implements Serializable {

    @EJB
    private ClienteDAO<Item> dao;
    private Cliente objeto;

    @EJB
    private CidadeDAO daoCidade;
    
    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(ClienteDAO dao) {
        this.dao = dao;
    }

    public ControleCliente() {
    }

    public String listar() {
        return "/privado/cliente/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Cliente();
    }

    public void salvar() {
        try {

            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.msgInformacao("Objeto persistido com sucesso.");
        } catch (Exception e) {
            Util.msgErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.msgErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.msgInformacao("Objeto removido com sucesso.");
        } catch (Exception e) {
            Util.msgErro("Erro ao remover objeto: " + e.getMessage());
        }
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }

}
