/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.modelo.Item;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Azzorf
 */
public class TesteItem {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteItem() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("finalPU_Local");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testar() {
        boolean exception = false;
        try {
            Item obj = new Item();
            obj.setNome("Vaciona");
            obj.setPreco(2.50);
            obj.setDescricao("Vacina contra as meljores doençcas de le world");
            obj.setEstoque(10);              
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
    
    
}
