package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Item;
import br.edu.ifsul.modelo.Veterinario;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.GenericDAO;
import br.edu.ifsul.dao.VeterinarioDAO;
import br.edu.ifsul.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleVeterinario")
@ViewScoped
public class ControleVeterinario implements Serializable {

    @EJB
    private VeterinarioDAO<Item> dao;
    private Veterinario objeto;

    @EJB
    private CidadeDAO daoCidade;
    
    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(VeterinarioDAO dao) {
        this.dao = dao;
    }

    public ControleVeterinario() {
    }

    public String listar() {
        return "/privado/veterinario/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Veterinario();
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

    public Veterinario getObjeto() {
        return objeto;
    }

    public void setObjeto(Veterinario objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }

}
