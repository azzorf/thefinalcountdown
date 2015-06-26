/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.dao;

import br.edu.ifsul.modelo.Veterinario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Azzorf
 */
@Stateful
public class VeterinarioDAO<T> extends GenericDAO<Veterinario> implements Serializable {

    public VeterinarioDAO() {
        super();
        super.setPersistentClass(Veterinario.class);
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("nome", "Nome", "like"));
        super.getListOrder().add(new Order("apelido", "Apelido", "like"));
        super.getListOrder().add(new Order("cidade.nome", "Cidade", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
    }

    @Override
    public Veterinario getObjectById(Integer id) throws Exception {
        Veterinario obj = super.getEm().find(Veterinario.class, id);
        obj.getAcessoUsuario().size();
        return obj;
    }

    public Boolean login(String usuario, String senha) {
        Query query = super.getEm().createQuery("from Veterinario where upper(email) = :login and upper(password) = :senha and ativo = true");
        query.setParameter("login", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if (!query.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Veterinario localizarUsuarioPorNome(String usuario) {
        Veterinario obj = (Veterinario) super.getEm().createQuery("from Veterinario where upper(email) = :login").
                setParameter("login", usuario.toUpperCase()).getSingleResult();
        obj.getAcessoUsuario().size();
        return obj;
    }
}