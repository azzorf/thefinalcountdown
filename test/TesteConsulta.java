/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.modelo.Animal;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Veterinario;
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
public class TesteConsulta {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteConsulta() {
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
            Consulta obj = new Consulta();
            obj.setVeterinario(em.find(Veterinario.class, 4));
            obj.setValorTotal(1234.23);
            obj.setDataPagamento(Calendar.getInstance());
            obj.setDataConsulta(Calendar.getInstance());
            obj.setAnimal(em.find(Animal.class, 4));
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
