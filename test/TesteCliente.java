/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifsul.modelo.Cidade;
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
public class TesteCliente {

    EntityManagerFactory emf;
    EntityManager em;

    public TesteCliente() {
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
            Cliente obj = new Cliente();
            obj.setNome("Iago Frozza");
            obj.setRg(1098596934);
            obj.setEmail("azzorf@gmail.com");
            obj.setEndereco("Rua Capitão Eleutério, 1477");
            obj.setTelefone("5499274219");
            obj.setCadastro(Calendar.getInstance());
            obj.setCpf("031.081.650-54");
            obj.setCidade(em.find(Cidade.class, 1));
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
