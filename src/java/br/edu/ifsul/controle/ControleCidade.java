package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.GenericDAO;
import br.edu.ifsul.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleCidade")
@ViewScoped
public class ControleCidade implements Serializable {

    @EJB
    private CidadeDAO<Cidade> dao;
    private Cidade objeto;

    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(CidadeDAO dao) {
        this.dao = dao;
    }

    public ControleCidade() {
    }

    public String listar() {
        return "/privado/cidade/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Cidade();
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

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

}
