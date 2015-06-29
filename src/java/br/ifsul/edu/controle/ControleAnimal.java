package br.ifsul.edu.controle;

import br.edu.ifsul.modelo.Animal;
import br.ifsul.edu.dao.AnimalDAO;
import br.ifsul.edu.dao.GenericDAO;
import br.ifsul.edu.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleAnimal")
@ViewScoped
public class ControleAnimal implements Serializable {

    @EJB
    private AnimalDAO<Animal> dao;
    private Animal objeto;


    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(AnimalDAO dao) {
        this.dao = dao;
    }

    public ControleAnimal() {
    }

    public String listar() {
        return "/privado/animal/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Animal();
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

    public Animal getObjeto() {
        return objeto;
    }

    public void setObjeto(Animal objeto) {
        this.objeto = objeto;
    }

}
