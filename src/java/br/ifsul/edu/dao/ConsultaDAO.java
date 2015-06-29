/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.dao;

import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Veterinario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Azzorf
 */
@Stateful
public class ConsultaDAO<T> extends GenericDAO<Consulta> implements Serializable {

    public ConsultaDAO() {
        super();
        // definindo a classe persistence
        super.setPersistentClass(Consulta.class);
        // definindo as ordenaçõe possiveis
        super.getListOrder().add(
                new Order("id", "ID", "="));
        super.getListOrder().add(
                new Order("veterinario.nome", "Veterinario", "like"));
        super.getListOrder().add(
                new Order("animal.nome", "Animal", "like"));
        // definir qual a ordenação padrão
        super.setCurrentOrder((Order) super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));

    }

    public Consulta getObjectById(Integer id) throws Exception {
        Consulta obj = super.getEm().find(Consulta.class, id);
        obj.getItens().size();
        return obj;
    }

}
