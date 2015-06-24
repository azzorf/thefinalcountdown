/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.modelo.Animal;
import br.edu.ifsul.modelo.Cliente;
import java.util.Calendar;
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
public class TesteAnimal {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteAnimal() {
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
            Animal obj = new Animal();
            obj.setNome("Minus");
            obj.setCliente(em.find(Cliente.class, 1));
            obj.setNascimento(Calendar.getInstance());
            obj.setPeso(54.5);
            obj.setRaca("SRD");
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
