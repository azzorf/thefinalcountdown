package br.ifsul.edu.controle;

import br.edu.ifsul.modelo.Item;
import br.ifsul.edu.dao.GenericDAO;
import br.ifsul.edu.dao.ItemDAO;
import br.ifsul.edu.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleItem")
@ViewScoped
public class ControleItem implements Serializable {

    @EJB
    private ItemDAO<Item> dao;
    private Item objeto;

    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(ItemDAO dao) {
        this.dao = dao;
    }

    public ControleItem() {
    }

    public String listar() {
        return "/privado/item/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Item();
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

    public Item getObjeto() {
        return objeto;
    }

    public void setObjeto(Item objeto) {
        this.objeto = objeto;
    }

}
