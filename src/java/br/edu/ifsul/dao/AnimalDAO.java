/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Animal;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Azzorf
 */
@Stateful
public class AnimalDAO<T> extends GenericDAO<Animal> implements Serializable {
    public AnimalDAO(){
        super();
        // definindo a classe persistence
        super.setPersistentClass(Animal.class);
        // definindo as ordenaçõe possiveis
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));        
        super.getListOrder().add(new Order("cliente.nome", "Cliente", "like"));        
        // definir qual a ordenação padrão
        super.setCurrentOrder((Order) super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));                
    }
}