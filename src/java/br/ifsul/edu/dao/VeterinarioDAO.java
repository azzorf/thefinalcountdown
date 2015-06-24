/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.dao;

import br.edu.ifsul.modelo.Veterinario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Azzorf
 */
@Stateful
public class VeterinarioDAO<T> extends GenericDAO<Veterinario> implements Serializable {
    public VeterinarioDAO(){
        super();
        // definindo a classe persistence
        super.setPersistentClass(Veterinario.class);
        // definindo as ordenaçõe possiveis
        super.getListOrder().add(
            new Order("id", "ID", "="));
        super.getListOrder().add(
            new Order("nome", "Nome", "like"));        
        // definir qual a ordenação padrão
        super.setCurrentOrder((Order) super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));                
    }
}