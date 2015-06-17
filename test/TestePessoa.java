/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Veterinario;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Azzorf
 */
public class TestePessoa {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePessoa() {
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
            Veterinario obj = new Veterinario();
            obj.setNome("Iago Frozza");
            obj.setAtivo(true);
            obj.setCargaHoraria(60.0);
            obj.setCrmv(859183512);
            obj.setRg(1098596934);
            obj.setEmail("azzorf@gmail.com");
            obj.setEndereco("Rua Capitão Eleutério, 1477");
            obj.setTelefone("5499274219");
            obj.setPassword("swordfish");
            obj.setEspecialidade("Caninos");
            obj.setCidade(em.find(Cidade.class, 4));
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
