package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.dao.AnimalDAO;
import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.GenericDAO;
import br.edu.ifsul.dao.ItemDAO;
import br.edu.ifsul.dao.VeterinarioDAO;
import br.edu.ifsul.util.Util;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {

    @EJB
    private AnimalDAO daoAnimal;
    
    @EJB
    private VeterinarioDAO daoVeterinario;
    
    @EJB
    private ItemDAO daoItem;
    
    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;

    public GenericDAO getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO dao) {
        this.dao = dao;
    }

    public ControleConsulta() {
    }

    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Consulta();
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

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public AnimalDAO getDaoAnimal() {
        return daoAnimal;
    }

    public void setDaoAnimal(AnimalDAO daoAnimal) {
        this.daoAnimal = daoAnimal;
    }

    public VeterinarioDAO getDaoVeterinario() {
        return daoVeterinario;
    }

    public void setDaoVeterinario(VeterinarioDAO daoVeterinario) {
        this.daoVeterinario = daoVeterinario;
    }

    public ItemDAO getDaoItem() {
        return daoItem;
    }

    public void setDaoItem(ItemDAO daoItem) {
        this.daoItem = daoItem;
    }

  
    
    

}
